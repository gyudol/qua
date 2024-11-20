package com.mulmeong.contest.application;

import com.mulmeong.contest.common.exception.BaseException;
import com.mulmeong.contest.common.response.BaseResponseStatus;
import com.mulmeong.contest.dto.in.ContestRequestDto;
import com.mulmeong.contest.dto.in.PostRequestDto;
import com.mulmeong.contest.dto.in.PostVoteRequestDto;
import com.mulmeong.contest.entity.Contest;
import com.mulmeong.contest.entity.ContestVote;
import com.mulmeong.contest.entity.ContestWinner;
import com.mulmeong.contest.infrastructure.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Service
public class ContestServiceImpl implements ContestService {

    private final ContestRepository contestRepository;
    private final ContestPostRepository contestPostRepository;
    private final ContestWinnerRepository contestWinnerRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private final ContestVoteRepository contestVoteRepository;

    private static final String VOTER_SET_KEY = "contest:%d:post:%s:voters";
    private static final String VOTE_COUNT_KEY = "contest:%d:post:%s:votes";
    private static final int TOP_WINNERS_COUNT = 3;

    @Override
    public void openContest(ContestRequestDto dto) {
        contestRepository.save(dto.toEntity());
    }

    @Override
    public void applyContest(PostRequestDto dto) {
        if (contestPostRepository.existsByContestIdAndMemberUuid(dto.getContestId(), dto.getMemberUuid())) {
            throw new BaseException(BaseResponseStatus.DUPLICATE_POST);
        }
        contestPostRepository.save(dto.toEntity());
    }

    @Override
    public void vote(PostVoteRequestDto voteRequestDto, String memberUuid) {
        String voterSetKey = String.format(VOTER_SET_KEY,
                voteRequestDto.getContestId(),
                voteRequestDto.getPostUuid());
        String voteCountKey = String.format(VOTE_COUNT_KEY,
                voteRequestDto.getContestId(),
                voteRequestDto.getPostUuid());

        // 0일 경우 ContestPost 중복 투표
        Long isNewVote = redisTemplate.opsForSet().add(voterSetKey, memberUuid);
        if (isNewVote == 0) {
            throw new BaseException(BaseResponseStatus.DUPLICATE_VOTE);
        }

        // key에 대한 value++
        redisTemplate.opsForValue().increment(voteCountKey);

        // redis TTL 7일
        redisTemplate.expire(voterSetKey, 7, TimeUnit.DAYS);
        redisTemplate.expire(voteCountKey, 7, TimeUnit.DAYS);
    }

    @Transactional
    @Scheduled(cron = "0 0 0 * * ?")    // 매일 정각
    public void selectWinners() {

        // 종료 일자가 어제인 콘테스트
        List<Contest> endedContests = contestRepository.findAll().stream()
                .filter(contest -> contest.getEndDate().isEqual(LocalDate.now().minusDays(1)))
                .toList();

        endedContests.stream().map(Contest::getId).forEach(this::calculateWinners);

    }

    @Override
    @Transactional
    public void calculateWinners(Long contestId) {
        String pattern = String.format("contest:%d:post:*:votes", contestId);
        Set<String> voteCountKeys = redisTemplate.keys(pattern);

        // 콘테스트 내에서 투표가 없을 경우
        if (voteCountKeys == null || voteCountKeys.isEmpty()) {
            throw new BaseException(BaseResponseStatus.NOT_EXIST);
        }

        // voteCount 기준 상위 3 post 추출
        List<Map.Entry<String, Long>> sortedVotes = voteCountKeys.stream()
                .map(key -> {
                    String[] parts = key.split(":");
                    String postUuid = parts[3];
                    String voteCountStr = redisTemplate.opsForValue().get(key);
                    Long voteCount = voteCountStr != null ? Long.parseLong(voteCountStr) : 0L;
                    return Map.entry(postUuid, voteCount);
                })
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .toList();

        for (int i = 0; i < Math.min(TOP_WINNERS_COUNT, sortedVotes.size()); i++) {
            Map.Entry<String, Long> entry = sortedVotes.get(i);
            String postUuid = entry.getKey();
            Long voteCount = entry.getValue();

            byte rank = (byte) (i + 1);

            ContestWinner winner = ContestWinner.builder()
                    .contestId(contestId)
                    .postUuid(postUuid)
                    .voteCount(voteCount)
                    .ranking(rank)
                    .build();
            contestWinnerRepository.save(winner);

            // 수상자들에 대한 투표자 기록
            String voterSetKey = String.format("contest:%d:post:%s:voters", contestId, postUuid);
            Set<String> voters = redisTemplate.opsForSet().members(voterSetKey);
            if (voters != null && !voters.isEmpty()) {
                voters.forEach(voterUuid -> {
                    ContestVote vote = ContestVote.builder()
                            .contestId(contestId)
                            .postUuid(postUuid)
                            .memberUuid(voterUuid)
                            .build();
                    contestVoteRepository.save(vote);
                });
            }
            // redis TTL 7일
            redisTemplate.expire(voterSetKey, 7, TimeUnit.DAYS);
        }

        // redis TTL 7일
        voteCountKeys.forEach(key -> redisTemplate.expire(key, 7, TimeUnit.DAYS));
    }

}

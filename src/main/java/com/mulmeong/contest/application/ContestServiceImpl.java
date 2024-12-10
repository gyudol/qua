package com.mulmeong.contest.application;

import com.mulmeong.contest.common.exception.BaseException;
import com.mulmeong.contest.common.response.BaseResponseStatus;
import com.mulmeong.contest.common.utils.CursorPage;
import com.mulmeong.contest.domain.entity.Contest;
import com.mulmeong.contest.dto.in.ContestQueryRequestDto;
import com.mulmeong.contest.dto.in.ContestRequestDto;
import com.mulmeong.contest.dto.in.PostRequestDto;
import com.mulmeong.contest.dto.in.PostVoteRequestDto;
import com.mulmeong.contest.dto.out.ContestResponseDto;

import com.mulmeong.contest.infrastructure.*;
import com.mulmeong.event.contest.consume.ContestStatusEvent;
import com.mulmeong.event.contest.consume.ContestVoteResultEvent;
import com.mulmeong.event.contest.produce.ContestPostCreateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ContestServiceImpl implements ContestService {

    private final EventPublisher eventPublisher;
    private final ContestRepository contestRepository;
    private final ContestPostRepository contestPostRepository;
    private final ContestResultRepository contestResultRepository;
    private final ContestCustomRepository contestCustomRepository;
    private final PostMediaRepository postMediaRepository;
    private final RedisTemplate<String, String> redisTemplate;

    private static final String VOTER_SET_KEY = "contest:%d:post:%s:voters";
    private static final String VOTE_COUNT_KEY = "contest:%d:post:votes";

    @Override
    @Transactional
    public void openContest(ContestRequestDto dto) {
        contestRepository.save(dto.toEntity());
    }

    @Override
    @Transactional
    public void applyContest(PostRequestDto dto) {
        if (contestPostRepository.existsByContestIdAndMemberUuid(dto.getContestId(), dto.getMemberUuid())) {
            throw new BaseException(BaseResponseStatus.DUPLICATE_POST);
        }
        contestPostRepository.save(dto.toEntity());
        postMediaRepository.save(dto.toMedia());
        eventPublisher.send(dto.toEvent());
    }

    @Override
    @Transactional
    public void vote(PostVoteRequestDto voteRequestDto, String memberUuid) {
        String voterSetKey = String.format(VOTER_SET_KEY,
                voteRequestDto.getContestId(),
                voteRequestDto.getPostUuid());

        String voteCountKey = String.format(VOTE_COUNT_KEY,
                voteRequestDto.getContestId());

        // 중복 투표 체크: 이미 투표한 유저가 있으면 중복으로 투표할 수 없도록 처리
        Long isNewVote = redisTemplate.opsForSet().add(voterSetKey, memberUuid);
        if (isNewVote == 0) {
            throw new BaseException(BaseResponseStatus.DUPLICATE_VOTE);
        }

        redisTemplate.opsForZSet().incrementScore(voteCountKey, voteRequestDto.getPostUuid(), 1);


    }

    @Override
    public CursorPage<ContestResponseDto> getContests(ContestQueryRequestDto requestDto) {
        return contestCustomRepository.getContests(requestDto);
    }

    @Override
    @Transactional
    public void createContestResult(ContestVoteResultEvent message) {
        contestResultRepository.save(message.toEntity(
                message.getContestId(),
                message.getMemberUuid(),
                message.getPostUuid(),
                message.getBadgeId(),
                message.getVoteCount(),
                message.getRanking()
        ));
    }

    @Override
    @Transactional
    public void altContestStatus(ContestStatusEvent message) {
        Contest contest = contestRepository.findById(message.getContestId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_EXIST)
        );
        log.info("id: {}", contest.getId());
        contestRepository.save(message.toEntity(contest));
    }


}

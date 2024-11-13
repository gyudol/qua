package com.mulmeong.member.interest.hashtag.application;

import com.mulmeong.member.interest.hashtag.dto.in.HashtagCreateDto;
import com.mulmeong.member.interest.hashtag.dto.in.HashtagUpdateDto;
import com.mulmeong.member.interest.hashtag.dto.out.HashtagDto;
import com.mulmeong.member.interest.hashtag.infrastructure.InterestHashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class InterestHashTagServiceImpl implements InterestHashtagService {

    private final InterestHashtagRepository interestHashTagRepository;

    /**
     * 회원의 관심 해시태그 생성.
     *
     * @param requestDto uuid + 해시태그명 리스트
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createInterestHashTag(HashtagCreateDto requestDto) {

        interestHashTagRepository.saveAll(requestDto.toEntities());
    }

    /**
     * 회원의 관심 해시태그 전체 목록 조회.
     *
     * @param memberUuid 회원 uuid
     * @return 관심 해시태그 목록
     */
    @Override
    @Transactional(readOnly = true)
    public List<HashtagDto> getInterestHashTagList(String memberUuid) {

        return interestHashTagRepository.findByMemberUuid(memberUuid).stream()
                .map(HashtagDto::fromEntity)
                .toList();
    }

    /**
     * 회원의 관심 해시태그 수정
     * 부분 수정이 아닌 전체 삭제 후 재삽입(데이터 양이 적고, 연산이 적기 때문).
     *
     * @param requestDto uuid + 해시태그명 리스트
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateInterestHashTag(HashtagUpdateDto requestDto) {

        interestHashTagRepository.deleteAllByMemberUuid(requestDto.getMemberUuid());
        interestHashTagRepository.flush();

        interestHashTagRepository.saveAll(requestDto.toEntities());
    }
}

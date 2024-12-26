package com.mulmeong.member.read.member.application;

import com.mulmeong.event.member.MemberCreateEvent;
import com.mulmeong.event.member.MemberNicknameUpdateEvent;
import com.mulmeong.event.member.MemberProfileImgUpdateEvent;
import com.mulmeong.member.read.common.exception.BaseException;
import com.mulmeong.member.read.member.document.Member;
import com.mulmeong.member.read.member.dto.out.CompactProfileDto;
import com.mulmeong.member.read.member.dto.out.MemberProfileDto;
import com.mulmeong.member.read.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static com.mulmeong.member.read.common.response.BaseResponseStatus.NOT_FOUND_MEMBER;

@RequiredArgsConstructor
@Service
public class MemberHttpHttpServiceImpl implements MemberHttpService {

    private final MemberRepository memberRepository;
    private final MongoTemplate mongoTemplate;

    /**
     * 닉네임으로 회원 프로필 조회 API
     * 등록/수정이 되지 않은 필드들은 기본값으로 반환.(예: 집계데이터들).
     *
     * @param nickname 회원 닉네임
     * @return 회원 프로필 DTO(Member Document와 같음)
     */
    @Override
    public MemberProfileDto getProfileByNickname(String nickname) {
        return memberRepository.findByNickname(nickname)
                .map(MemberProfileDto::fromDocument)
                .orElseThrow(() -> new BaseException(NOT_FOUND_MEMBER));
    }

    /**
     * 회원 uuid로 회원 프로필 조회 API
     * 등록/수정이 되지 않은 필드들은 기본값으로 반환됩니다.(예: 집계데이터들).
     *
     * @param memberUuid 회원 uuid
     * @return 회원 프로필 DTO(Member Document와 같음)
     */
    @Override
    public MemberProfileDto getProfileByMemberUuid(String memberUuid) {
        return memberRepository.findByMemberUuid(memberUuid)
                .map(MemberProfileDto::fromDocument)
                .orElseThrow(() -> new BaseException(NOT_FOUND_MEMBER));
    }

    /**
     * 회원 Compact 프로필 조회 API
     * 피드, 쇼츠 등에서 사용되는 간단한 회원 프로필 조회.
     *
     * @param memberUuid 회원 닉네임
     * @return 회원 프로필 DTO중 일부
     */
    @Override
    public CompactProfileDto getCompactProfile(String memberUuid) {
        return memberRepository.findByMemberUuid(memberUuid)
                .map(CompactProfileDto::fromDocument)
                .orElseThrow(() -> new BaseException(NOT_FOUND_MEMBER));
    }
}

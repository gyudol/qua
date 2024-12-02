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
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MongoTemplate mongoTemplate;

    /**
     * 회원 Read DB 생성.
     * Write DB에서 회원 생성 이벤트를 받아 처리.
     *
     * @param event 회원 생성 Event
     */
    @Override
    public void createMember(MemberCreateEvent event) {

        memberRepository.save(event.toEntity());
    }

    /**
     * 회원 Read DB의 닉네임 수정.
     * Write DB에서 닉네임 수정 이벤트를 받아 처리.
     *
     * @param event 닉네임 수정 Event
     */
    @Override
    public void updateNickname(MemberNicknameUpdateEvent event) {
        updateMemberField(event.getMemberUuid(), "nickname", event.getNickname());
    }

    /**
     * 회원 Read DB의 프로필 이미지 수정.
     * Write DB에서 프로필 이미지 수정 이벤트를 받아 처리.
     *
     * @param event 프로필 이미지 수정 Event.
     */
    @Override
    public void updateProfileImage(MemberProfileImgUpdateEvent event) {
        updateMemberField(event.getMemberUuid(), "profileImageUrl", event.getProfileImgUrl());
    }

    /**
     * 회원 프로필 조회 API
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
     * 회원 프로필 조회 API
     * 등록/수정이 되지 않은 필드들은 기본값으로 반환됩니다.(예: 집계데이터들).
     *
     * @param memberUuid 회원 닉네임
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

    /**
     * 회원 필드 업데이트하는 private 메서드.
     *
     * @param memberUuid 업데이트할 회원의 uuid
     * @param field      업데이트할 필드(닉네임, 프로필 이미지 URL...)
     * @param value      업데이트할 값
     */
    private void updateMemberField(String memberUuid, String field, Object value) {
        Query query = new Query(Criteria.where("memberUuid").is(memberUuid));
        Update update = new Update().set(field, value);
        mongoTemplate.updateFirst(query, update, Member.class);
    }
}

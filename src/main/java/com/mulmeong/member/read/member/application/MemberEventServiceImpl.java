package com.mulmeong.member.read.member.application;

import com.mulmeong.event.member.MemberBadgeUpdateEvent;
import com.mulmeong.event.member.MemberCreateEvent;
import com.mulmeong.event.member.MemberNicknameUpdateEvent;
import com.mulmeong.event.member.MemberProfileImgUpdateEvent;
import com.mulmeong.member.read.member.document.Member;
import com.mulmeong.member.read.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberEventServiceImpl implements MemberEventService {

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
     * 회원 Read DB의 장착 뱃지 수정.
     * 뱃지를 해제한 경우에는 null로 업데이트.
     *
     * @param event 뱃지 장착/해제 이벤트
     */
    @Override
    public void updateEquippedBadge(MemberBadgeUpdateEvent event) {
        updateMemberField(event.getMemberUuid(),
                "equippedBadge",
                event.isEquipped() ? event.toEntity() : null);
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

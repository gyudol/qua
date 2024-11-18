package com.mulmeong.member.read.member.application;

import com.mulmeong.event.member.MemberCreateEvent;
import com.mulmeong.event.member.MemberNicknameUpdateEvent;
import com.mulmeong.event.member.MemberProfileImgUpdateEvent;
import com.mulmeong.member.read.common.exception.BaseException;
import com.mulmeong.member.read.member.document.Member;
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
     *
     * @param event 회원 생성 Event
     */
    @Override
    public void createMember(MemberCreateEvent event) {

        memberRepository.save(event.toEntity());
    }

    /**
     * 회원 Read DB의 닉네임 수정.
     * {@link com.mulmeong.member.read.member.kafka.KafkaConsumer}로부터 Kafka 이벤트를 받아 처리.
     *
     * @param event 닉네임 수정 Event
     */
    @Override
    public void updateNickname(MemberNicknameUpdateEvent event) {
        updateMemberField(event.getMemberUuid(), "nickname", event.getNickname());
    }

    /**
     * 회원 Read DB의 프로필 이미지 수정.
     * {@link com.mulmeong.member.read.member.kafka.KafkaConsumer}로부터 Kafka 이벤트를 받아 처리.
     *
     * @param event 프로필 이미지 수정 Event.
     */
    @Override
    public void updateProfileImage(MemberProfileImgUpdateEvent event) {
        updateMemberField(event.getMemberUuid(), "nickname", event.getProfileImgUrl());
    }

    /**
     * 회원 프로필 조회 API
     * 등록/수정이 되지 않은 필드들은 null로 반환됩니다.
     *
     * @param nickname 회원 닉네임(MemberUuid 노출 최소화를 위해 API 정의서에 닉네임으로 정의)
     * @return 회원 프로필DTO
     */
    @Override
    public MemberProfileDto getMemberProfile(String nickname) {
        return memberRepository.findByNickname(nickname)
                .map(MemberProfileDto::fromEntity)
                .orElseThrow(() -> new BaseException(NOT_FOUND_MEMBER));
    }

    /**
     * 회원 필드 업데이트 메서드.
     *
     * @param memberUuid 업데이트할 회원의 uuid
     * @param field      업데이트할 필드
     * @param value      업데이트할 값
     */
    private void updateMemberField(String memberUuid, String field, Object value) {
        Query query = new Query(Criteria.where("memberUuid").is(memberUuid));
        Update update = new Update().set(field, value);
        mongoTemplate.updateFirst(query, update, Member.class);
    }
}

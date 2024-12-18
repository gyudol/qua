package com.mulmeong.batchserver.member.application;

import com.mulmeong.batchserver.member.domain.document.MemberRead;
import com.mulmeong.batchserver.member.infrastructure.repository.MemberReadRepository;
import com.mulmeong.batchserver.utility.application.UtilityKafkaPublisher;
import com.mulmeong.batchserver.utility.infrastructure.repository.FollowRepository;
import com.mulmeong.event.utility.consume.FeedCreateEvent;
import com.mulmeong.event.utility.consume.ShortsCreateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberReadRepository memberReadRepository;
    private final MongoTemplate feedMongoTemplate;
    private final MongoTemplate shortsMongoTemplate;

    public MemberServiceImpl(
            @Qualifier("shortsReadMongoTemplate") MongoTemplate shortsMongoTemplate,
            @Qualifier("feedReadMongoTemplate") MongoTemplate feedMongoTemplate,
            MemberReadRepository memberReadRepository) {
        this.shortsMongoTemplate = shortsMongoTemplate;
        this.feedMongoTemplate = feedMongoTemplate;
        this.memberReadRepository = memberReadRepository;
    }

    @Override
    public void updateFeedCount(FeedCreateEvent message) {
        String memberUuid = message.getMemberUuid();

        MemberRead memberRead = memberReadRepository.findByMemberUuid(memberUuid);

        long feedCount = feedMongoTemplate.count(
                Query.query(Criteria.where("memberUuid").is(memberUuid)),
                "feed"
        );
        memberReadRepository.save(
                MemberRead.builder()
                        .memberUuid(memberRead.getMemberUuid())
                        .nickname(memberRead.getNickname())
                        .profileImageUrl(memberRead.getProfileImageUrl())
                        .createdAt(memberRead.getCreatedAt())
                        .gradeId(memberRead.getGradeId())
                        .equippedBadgeId(memberRead.getEquippedBadgeId())
                        .followerCount(memberRead.getFollowerCount())
                        .followingCount(memberRead.getFollowingCount())
                        .feedCount((int) feedCount)
                        .shortsCount(memberRead.getShortsCount())
                        .build()
        );
    }

    @Override
    public void updateShortsCount(ShortsCreateEvent message) {
        String memberUuid = message.getMemberUuid();

        MemberRead memberRead = memberReadRepository.findByMemberUuid(memberUuid);

        long shortsCount = shortsMongoTemplate.count(
                Query.query(Criteria.where("memberUuid").is(memberUuid)),
                "shorts"
        );
        memberReadRepository.save(
                MemberRead.builder()
                        .memberUuid(memberRead.getMemberUuid())
                        .nickname(memberRead.getNickname())
                        .profileImageUrl(memberRead.getProfileImageUrl())
                        .createdAt(memberRead.getCreatedAt())
                        .gradeId(memberRead.getGradeId())
                        .equippedBadgeId(memberRead.getEquippedBadgeId())
                        .followerCount(memberRead.getFollowerCount())
                        .followingCount(memberRead.getFollowingCount())
                        .feedCount(memberRead.getFeedCount())
                        .shortsCount((int)shortsCount)
                        .build()
        );
    }
}

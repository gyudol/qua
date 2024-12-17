package com.mulmeong.batchserver.utility.application;

import com.mulmeong.batchserver.member.domain.document.MemberRead;
import com.mulmeong.batchserver.member.infrastructure.repository.MemberReadRepository;
import com.mulmeong.batchserver.utility.domain.document.Follow;
import com.mulmeong.batchserver.utility.infrastructure.repository.FollowRepository;
import com.mulmeong.event.utility.consume.FeedCreateEvent;
import com.mulmeong.event.utility.consume.FollowCreateEvent;
import com.mulmeong.event.utility.consume.ShortsCreateEvent;
import com.mulmeong.event.utility.consume.UnfollowEvent;
import com.mulmeong.event.utility.produce.FeedCreatedFollowersEvent;
import com.mulmeong.event.utility.produce.ShortsCreatedFollowersEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class FollowServiceImpl implements FollowService {

    private final UtilityKafkaPublisher eventPublisher;
    private final FollowRepository followRepository;
    private final MemberReadRepository memberReadRepository;


    @Override
    public void createFeedFollowerAlert(FeedCreateEvent message) {

        List<Follow> followers = followRepository.findAllByTargetUuid(message.getMemberUuid());
        List<String> followerUuids = followers.stream()
                .map(Follow::getSourceUuid)
                .collect(Collectors.toList());
        log.info("followers: {}", followerUuids);
        eventPublisher.send(FeedCreatedFollowersEvent.toDto(
                message.getFeedUuid(),
                message.getMemberUuid(),
                followerUuids,
                message.getTitle()
        ));
    }

    @Override
    public void createShortsFollowerAlert(ShortsCreateEvent message) {

        List<Follow> followers = followRepository.findAllByTargetUuid(message.getMemberUuid());
        List<String> followerUuids = followers.stream()
                .map(Follow::getSourceUuid)
                .collect(Collectors.toList());
        log.info("followers: {}", followerUuids);
        eventPublisher.send(ShortsCreatedFollowersEvent.toDto(
                message.getShortsUuid(),
                message.getMemberUuid(),
                followerUuids,
                message.getTitle()
        ));
    }

    @Override
    public void createFollowerRenew(FollowCreateEvent message) {

        MemberRead followerUpdateMember = memberReadRepository.findByMemberUuid(message.getTargetUuid());
        MemberRead followingUpdateMember = memberReadRepository.findByMemberUuid(message.getSourceUuid());

        int followerCount = followRepository.countByTargetUuid(message.getTargetUuid());
        int followingCount = followRepository.countBySourceUuid(message.getSourceUuid());

        memberReadRepository.save(message.toFollowerUp(followerUpdateMember, followerCount));
        memberReadRepository.save(message.toFollowingUp(followingUpdateMember, followingCount));

    }

    @Override
    public void createFollowerRenew(UnfollowEvent message) {
        MemberRead followerUpdateMember = memberReadRepository.findByMemberUuid(message.getTargetUuid());
        MemberRead followingUpdateMember = memberReadRepository.findByMemberUuid(message.getSourceUuid());

        int followerCount = followRepository.countByTargetUuid(message.getTargetUuid());
        int followingCount = followRepository.countBySourceUuid(message.getSourceUuid());

        memberReadRepository.save(message.toFollowerDown(followerUpdateMember, followerCount));
        memberReadRepository.save(message.toFollowingDown(followingUpdateMember, followingCount));
    }

}

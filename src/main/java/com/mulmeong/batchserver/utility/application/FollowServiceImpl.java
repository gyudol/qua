package com.mulmeong.batchserver.utility.application;

import com.mulmeong.batchserver.utility.domain.document.Follow;
import com.mulmeong.batchserver.utility.infrastructure.repository.FollowRepository;
import com.mulmeong.event.utility.consume.FeedCreateEvent;
import com.mulmeong.event.utility.consume.ShortsCreateEvent;
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
}

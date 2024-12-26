package com.mulmeong.utility.application.service;

import com.mulmeong.event.produce.FollowCreateEvent;
import com.mulmeong.event.produce.UnfollowEvent;
import com.mulmeong.utility.application.EventPublisher;
import com.mulmeong.utility.application.port.in.FollowUseCase;
import com.mulmeong.utility.application.port.in.dto.FollowRequestDto;
import com.mulmeong.utility.application.port.out.FollowPort;
import com.mulmeong.utility.common.exception.BaseException;
import com.mulmeong.utility.common.response.BaseResponseStatus;
import com.mulmeong.utility.common.utils.CursorPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class FollowService implements FollowUseCase {

    private final FollowPort followPort;
    private final EventPublisher eventPublisher;

    @Override
    public void follow(FollowRequestDto followRequestDto) {

        if (followPort.existsBySourceUuidAndTargetUuid(followRequestDto)) {
            throw  new BaseException(BaseResponseStatus.DUPLICATE_FOLLOW);
        } else {
            eventPublisher.sendFollowEvent(FollowCreateEvent.toDto(followRequestDto));
            followPort.saveFollow(followRequestDto);
        }
    }

    @Override
    public void unfollow(FollowRequestDto followRequestDto) {
        eventPublisher.sendUnfollowEvent(UnfollowEvent.toDto(followRequestDto));
        followPort.unfollow(followRequestDto);
    }

    @Override
    public boolean followStatus(FollowRequestDto followRequestDto) {
        return followPort.followStatus(followRequestDto);
    }

    @Override
    public CursorPage<String> getFollowers(String memberUuid, String lastId, int pageSize, int pageNo) {
        return followPort.getFollowers(memberUuid, lastId, pageSize, pageNo);
    }

    @Override
    public CursorPage<String> getFollowings(String memberUuid, String lastId, int pageSize, int pageNo) {
        return followPort.getFollowings(memberUuid, lastId, pageSize, pageNo);
    }

    @Override
    public List<String> getAllFollowings(String memberUuid) {
        return followPort.getAllFollowings(memberUuid);
    }


}

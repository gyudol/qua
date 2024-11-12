package com.mulmeong.utility.application.service;

import com.mulmeong.utility.application.port.in.FollowUseCase;
import com.mulmeong.utility.application.port.in.dto.FollowRequestDto;
import com.mulmeong.utility.application.port.out.FollowPort;
import com.mulmeong.utility.common.exception.BaseException;
import com.mulmeong.utility.common.response.BaseResponseStatus;
import com.mulmeong.utility.common.utils.CursorPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class FollowService implements FollowUseCase {

    private final FollowPort followPort;

    @Override
    public void follow(FollowRequestDto followRequestDto) {

        if (followPort.existsBySourceUuidAndTargetUuid(followRequestDto)) {
            throw  new BaseException(BaseResponseStatus.DUPLICATE_FOLLOW);
        } else {
            followPort.saveFollow(followRequestDto);
        }
    }

    @Override
    public void unfollow(FollowRequestDto followRequestDto) {
        followPort.unfollow(followRequestDto);
    }

    @Override
    public boolean followStatus(FollowRequestDto followRequestDto) {
        return followPort.followStatus(followRequestDto);
    }

    @Override
    public CursorPage<String> getFollowers(String memberUuid, String lastId, int pageSize) {
        return followPort.getFollowers(memberUuid, lastId, pageSize);
    }


}

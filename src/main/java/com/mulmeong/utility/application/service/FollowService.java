package com.mulmeong.utility.application.service;

import com.mulmeong.utility.application.port.in.FollowUseCase;
import com.mulmeong.utility.application.port.in.dto.FollowRequestDto;
import com.mulmeong.utility.application.port.out.FollowPort;
import com.mulmeong.utility.common.exception.BaseException;
import com.mulmeong.utility.common.response.BaseResponseStatus;
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

}

package com.mulmeong.utility.application.port.out;

import com.mulmeong.utility.application.port.in.dto.FollowRequestDto;

public interface FollowPort {

    boolean existsBySourceUuidAndTargetUuid(FollowRequestDto followRequestDto);

    void saveFollow(FollowRequestDto followRequestDto);

    void unfollow(FollowRequestDto followRequestDto);

    boolean followStatus(FollowRequestDto followRequestDto);
}

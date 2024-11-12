package com.mulmeong.utility.application.port.out;

import com.mulmeong.utility.application.port.in.dto.FollowRequestDto;
import com.mulmeong.utility.common.utils.CursorPage;

public interface FollowPort {

    boolean existsBySourceUuidAndTargetUuid(FollowRequestDto followRequestDto);

    void saveFollow(FollowRequestDto followRequestDto);

    void unfollow(FollowRequestDto followRequestDto);

    boolean followStatus(FollowRequestDto followRequestDto);

    CursorPage<String> getFollowers(String memberUuid, String lastId, int pageSize);

    CursorPage<String> getFollowings(String memberUuid, String lastId, int pageSize);
}


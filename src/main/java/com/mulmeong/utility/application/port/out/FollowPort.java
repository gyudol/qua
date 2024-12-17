package com.mulmeong.utility.application.port.out;

import com.mulmeong.utility.application.port.in.dto.FollowRequestDto;
import com.mulmeong.utility.common.utils.CursorPage;

import java.util.List;

public interface FollowPort {

    boolean existsBySourceUuidAndTargetUuid(FollowRequestDto followRequestDto);

    void saveFollow(FollowRequestDto followRequestDto);

    void unfollow(FollowRequestDto followRequestDto);

    boolean followStatus(FollowRequestDto followRequestDto);

    CursorPage<String> getFollowers(String memberUuid, String lastId, int pageSize, int pageNo);

    CursorPage<String> getFollowings(String memberUuid, String lastId, int pageSize, int pageNo);

    List<String> getAllFollowings(String memberUuid);
}


package com.mulmeong.utility.application.port.in;

import com.mulmeong.utility.application.port.in.dto.FollowRequestDto;
import com.mulmeong.utility.common.utils.CursorPage;

import java.util.List;

public interface FollowUseCase {


    void follow(FollowRequestDto followRequestDto);

    void unfollow(FollowRequestDto followRequestDto);

    boolean followStatus(FollowRequestDto followRequestDto);

    CursorPage<String> getFollowers(String memberUuid, String lastId, int pageSize, int pageNo);

    CursorPage<String> getFollowings(String memberUuid, String lastId, int pageSize, int pageNo);

    List<String> getAllFollowings(String memberUuid);
}

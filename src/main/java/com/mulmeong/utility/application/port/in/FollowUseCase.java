package com.mulmeong.utility.application.port.in;

import com.mulmeong.utility.application.port.in.dto.FollowRequestDto;
import com.mulmeong.utility.common.response.BaseResponseStatus;
import com.mulmeong.utility.common.utils.CursorPage;

public interface FollowUseCase {


    void follow(FollowRequestDto followRequestDto);

    void unfollow(FollowRequestDto followRequestDto);

    boolean followStatus(FollowRequestDto followRequestDto);

    CursorPage<String> getFollowers(String memberUuid, String lastId, int pageSize);

    CursorPage<String> getFollowings(String memberUuid, String lastId, int pageSize);
}

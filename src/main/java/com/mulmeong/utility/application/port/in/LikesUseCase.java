package com.mulmeong.utility.application.port.in;

import com.mulmeong.utility.application.port.in.dto.LikesRenewRequestDto;
import com.mulmeong.utility.application.port.in.dto.LikesRequestDto;
import com.mulmeong.utility.common.utils.CursorPage;

public interface LikesUseCase {

    boolean isChecked(LikesRequestDto likesRequestDto);

    void likes(LikesRequestDto likesRequestDto);

    CursorPage<String> getLikes(String memberUuid, String kind, String lastId, int pageSize, int pageNo);

    void renewValidate(LikesRenewRequestDto requestDto);
}

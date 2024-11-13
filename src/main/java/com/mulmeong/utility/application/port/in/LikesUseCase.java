package com.mulmeong.utility.application.port.in;

import com.mulmeong.utility.application.port.in.dto.LikesListRequestDto;
import com.mulmeong.utility.application.port.in.dto.LikesRequestDto;
import com.mulmeong.utility.common.utils.CursorPage;

import java.util.List;

public interface LikesUseCase {

    boolean isChecked(LikesRequestDto likesRequestDto);

    void likes(LikesRequestDto likesRequestDto);

    CursorPage<String> getLikes(String memberUuid, String kind, String lastId, int pageSize);
}

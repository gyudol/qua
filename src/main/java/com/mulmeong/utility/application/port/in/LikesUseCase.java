package com.mulmeong.utility.application.port.in;

import com.mulmeong.utility.application.port.in.dto.LikesRequestDto;

public interface LikesUseCase {

    boolean isChecked(LikesRequestDto likesRequestDto);

    void likes(LikesRequestDto likesRequestDto);
}

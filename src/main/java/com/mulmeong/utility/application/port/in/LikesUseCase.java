package com.mulmeong.utility.application.port.in;

import com.mulmeong.utility.application.port.in.dto.LikesListRequestDto;
import com.mulmeong.utility.application.port.in.dto.LikesRequestDto;

import java.util.List;

public interface LikesUseCase {

    boolean isChecked(LikesRequestDto likesRequestDto);

    void likes(LikesRequestDto likesRequestDto);

    List<String> getLikes(LikesListRequestDto likesListRequestDto);
}

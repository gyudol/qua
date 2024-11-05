package com.mulmeong.utility.application.port.in;

import com.mulmeong.utility.application.port.in.dto.DislikeRequestDto;

public interface DislikeUseCase {

    void dislike(DislikeRequestDto dislikeRequestDto);
}

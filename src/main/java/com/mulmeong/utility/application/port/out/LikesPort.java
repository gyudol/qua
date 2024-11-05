package com.mulmeong.utility.application.port.out;

import com.mulmeong.utility.application.port.in.dto.LikesRequestDto;
import com.mulmeong.utility.application.port.out.dto.LikesEntityResponseDto;
import com.mulmeong.utility.application.port.out.dto.LikesResponseDto;

import java.util.Optional;

public interface LikesPort {
    void saveLikes(LikesResponseDto likesResponseDto);

    void updateLikes(LikesEntityResponseDto likesEntityResponseDto);

    Optional<LikesEntityResponseDto> findByMemberAndKind(LikesRequestDto likesRequestDto);
}

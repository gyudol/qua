package com.mulmeong.utility.application.port.out;

import com.mulmeong.utility.application.port.in.dto.LikesListRequestDto;
import com.mulmeong.utility.application.port.in.dto.LikesRequestDto;
import com.mulmeong.utility.application.port.out.dto.LikesEntityResponseDto;
import com.mulmeong.utility.application.port.out.dto.LikesResponseDto;
import com.mulmeong.utility.common.utils.CursorPage;

import java.util.List;
import java.util.Optional;

public interface LikesPort {
    void saveLikes(LikesResponseDto likesResponseDto);

    void updateLikes(LikesEntityResponseDto likesEntityResponseDto);

    Optional<LikesEntityResponseDto> findByMemberAndKind(LikesRequestDto likesRequestDto);

    CursorPage<String> getLikes(String memberUuid, String kind, String lastId, int pageSize);
}

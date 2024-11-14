package com.mulmeong.utility.application.port.out;

import com.mulmeong.utility.application.port.in.dto.DislikeListRequestDto;
import com.mulmeong.utility.application.port.in.dto.DislikeRequestDto;
import com.mulmeong.utility.application.port.out.dto.DislikeEntityResponseDto;
import com.mulmeong.utility.application.port.out.dto.DislikeResponseDto;

import java.util.List;
import java.util.Optional;

public interface DislikePort {
    void saveDislike(DislikeResponseDto dislikeResponseDto);

    void updateDislike(DislikeEntityResponseDto dislikeEntityResponseDto);

    Optional<DislikeEntityResponseDto> findByMemberAndKind(DislikeRequestDto dislikeRequestDto);

    List<DislikeEntityResponseDto> getByMemberAndKind(DislikeListRequestDto dislikeListRequestDto);
}

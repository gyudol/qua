package com.mulmeong.utility.application.mapper;

import com.mulmeong.utility.application.port.out.dto.DislikeEntityResponseDto;
import com.mulmeong.utility.application.port.out.dto.DislikeResponseDto;
import com.mulmeong.utility.application.port.out.dto.LikesEntityResponseDto;
import com.mulmeong.utility.application.port.out.dto.LikesResponseDto;
import com.mulmeong.utility.domain.model.Dislike;
import com.mulmeong.utility.domain.model.Likes;
import org.springframework.stereotype.Component;

@Component
public class DislikeDtoMapper {

    public DislikeResponseDto toDto(Dislike dislike) {
        return DislikeResponseDto.builder()
                .memberUuid(dislike.getMemberUuid())
                .kind(dislike.getKind())
                .kindUuid(dislike.getKindUuid())
                .status(dislike.isStatus())
                .build();
    }

    public Dislike toEntity(DislikeEntityResponseDto dislikeEntityResponseDto) {
        return Dislike.builder()
                .id(dislikeEntityResponseDto.getId())
                .memberUuid(dislikeEntityResponseDto.getMemberUuid())
                .kind(dislikeEntityResponseDto.getKind())
                .kindUuid(dislikeEntityResponseDto.getKindUuid())
                .status(dislikeEntityResponseDto.isStatus())
                .build();
    }

    public DislikeEntityResponseDto toEntityDto(Dislike dislike) {
        return DislikeEntityResponseDto.builder()
                .id(dislike.getId())
                .memberUuid(dislike.getMemberUuid())
                .kind(dislike.getKind())
                .kindUuid(dislike.getKindUuid())
                .status(dislike.isStatus())
                .build();
    }

}

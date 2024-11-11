package com.mulmeong.utility.adapter.out.infrastructure.mongo.mapper;

import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.DislikeEntity;
import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.LikesEntity;
import com.mulmeong.utility.application.port.out.dto.DislikeEntityResponseDto;
import com.mulmeong.utility.application.port.out.dto.DislikeResponseDto;
import com.mulmeong.utility.application.port.out.dto.LikesEntityResponseDto;
import com.mulmeong.utility.application.port.out.dto.LikesResponseDto;
import org.springframework.stereotype.Component;

@Component
public class DislikeEntityMapper {

    public DislikeEntity toEntity(DislikeResponseDto dto) {
        return DislikeEntity.builder()
                .memberUuid(dto.getMemberUuid())
                .kind(dto.getKind())
                .kindUuid(dto.getKindUuid())
                .status(dto.isStatus())
                .build();
    }

    public DislikeEntity toUpdate(DislikeEntityResponseDto dto) {
        return DislikeEntity.builder()
                .id(dto.getId())
                .memberUuid(dto.getMemberUuid())
                .kind(dto.getKind())
                .kindUuid(dto.getKindUuid())
                .status(dto.isStatus())
                .build();
    }

    public DislikeEntityResponseDto toDto(DislikeEntity entity) {
        return DislikeEntityResponseDto.builder()
                .id(entity.getId())
                .memberUuid(entity.getMemberUuid())
                .kind(entity.getKind())
                .kindUuid(entity.getKindUuid())
                .status(entity.isStatus())
                .build();
    }

}

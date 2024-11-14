package com.mulmeong.utility.adapter.out.infrastructure.mongo.mapper;

import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.LikesEntity;
import com.mulmeong.utility.application.port.out.dto.LikesEntityResponseDto;
import com.mulmeong.utility.application.port.out.dto.LikesResponseDto;
import org.springframework.stereotype.Component;

@Component
public class LikesEntityMapper {

    public LikesEntity toEntity(LikesResponseDto dto) {
        return LikesEntity.builder()
                .memberUuid(dto.getMemberUuid())
                .kind(dto.getKind())
                .kindUuid(dto.getKindUuid())
                .status(dto.isStatus())
                .build();
    }

    public LikesEntity toUpdate(LikesEntityResponseDto dto) {
        return LikesEntity.builder()
                .id(dto.getId())
                .memberUuid(dto.getMemberUuid())
                .kind(dto.getKind())
                .kindUuid(dto.getKindUuid())
                .status(dto.isStatus())
                .build();
    }

    public LikesEntityResponseDto toDto(LikesEntity entity) {
        return LikesEntityResponseDto.builder()
                .id(entity.getId())
                .memberUuid(entity.getMemberUuid())
                .kind(entity.getKind())
                .kindUuid(entity.getKindUuid())
                .status(entity.isStatus())
                .build();
    }

}

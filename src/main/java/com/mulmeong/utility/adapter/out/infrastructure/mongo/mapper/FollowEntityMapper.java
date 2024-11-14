package com.mulmeong.utility.adapter.out.infrastructure.mongo.mapper;

import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.FollowEntity;
import com.mulmeong.utility.application.port.in.dto.FollowRequestDto;
import org.springframework.stereotype.Component;

@Component
public class FollowEntityMapper {

    public FollowEntity toEntity(FollowRequestDto requestDto) {
        return FollowEntity.builder()
                .sourceUuid(requestDto.getSourceUuid())
                .targetUuid(requestDto.getTargetUuid())
                .build();
    }

}

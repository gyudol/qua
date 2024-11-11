package com.mulmeong.utility.application.mapper;

import com.mulmeong.utility.application.port.out.LikesPort;
import com.mulmeong.utility.application.port.out.dto.LikesEntityResponseDto;
import com.mulmeong.utility.application.port.out.dto.LikesResponseDto;
import com.mulmeong.utility.domain.model.Likes;
import org.springframework.stereotype.Component;

@Component
public class LikesDtoMapper {

    public LikesResponseDto toDto(Likes likes) {
        return LikesResponseDto.builder()
                .memberUuid(likes.getMemberUuid())
                .kind(likes.getKind())
                .kindUuid(likes.getKindUuid())
                .status(likes.isStatus())
                .build();
    }

    public Likes toEntity(LikesEntityResponseDto likesEntityResponseDto) {
        return Likes.builder()
                .id(likesEntityResponseDto.getId())
                .memberUuid(likesEntityResponseDto.getMemberUuid())
                .kind(likesEntityResponseDto.getKind())
                .kindUuid(likesEntityResponseDto.getKindUuid())
                .status(likesEntityResponseDto.isStatus())
                .build();
    }

    public LikesEntityResponseDto toEntityDto(Likes likes) {
        return LikesEntityResponseDto.builder()
                .id(likes.getId())
                .memberUuid(likes.getMemberUuid())
                .kind(likes.getKind())
                .kindUuid(likes.getKindUuid())
                .status(likes.isStatus())
                .build();
    }

}

package com.mulmeong.utility.application.service;

import com.mulmeong.utility.application.mapper.LikesDtoMapper;
import com.mulmeong.utility.application.port.in.LikesUseCase;
import com.mulmeong.utility.application.port.in.dto.LikesListRequestDto;
import com.mulmeong.utility.application.port.in.dto.LikesRequestDto;
import com.mulmeong.utility.application.port.out.LikesPort;
import com.mulmeong.utility.application.port.out.dto.LikesEntityResponseDto;
import com.mulmeong.utility.domain.model.Likes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class LikesService implements LikesUseCase {

    private final LikesPort likesPort;
    private final LikesDtoMapper likesDtoMapper;

    @Override
    public void likes(LikesRequestDto likesRequestDto) {

        Optional<LikesEntityResponseDto> existingLikesDto = likesPort.findByMemberAndKind(likesRequestDto);


        if (existingLikesDto.isPresent()) {
            // 좋아요가 이미 존재하면 상태를 토글
            Likes likes = likesDtoMapper.toEntity(existingLikesDto.get());
            likes.toggleStatus();
            likesPort.updateLikes(likesDtoMapper.toEntityDto(likes)); // 업데이트된 리액션 저장
        } else {
            // 좋아요가 존재하지 않으면 새 좋아요 생성
            Likes newLikes = Likes.builder()
                    .memberUuid(likesRequestDto.getMemberUuid())
                    .kind(likesRequestDto.getKind())
                    .kindUuid(likesRequestDto.getKindUuid())
                    .status(true) // 기본적으로 좋아요
                    .build();
            likesPort.saveLikes(likesDtoMapper.toDto(newLikes)); // 새 리액션 저장
        }

    }

    @Override
    public boolean isChecked(LikesRequestDto likesRequestDto) {
        return likesPort.findByMemberAndKind(likesRequestDto)
                .map(LikesEntityResponseDto::isStatus)
                .orElse(false);
    }

    public List<String> getLikes(LikesListRequestDto likesListRequestDto) {
        List<LikesEntityResponseDto> likedEntities = likesPort.getByMemberAndKind(likesListRequestDto);
        return likedEntities.stream()
                .map(LikesEntityResponseDto::getKindUuid)
                .collect(Collectors.toList());
    }


}

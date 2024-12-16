package com.mulmeong.utility.application.service;

import com.mulmeong.event.produce.LikeCreateEvent;
import com.mulmeong.event.produce.LikeRenewCreateEvent;
import com.mulmeong.utility.application.EventPublisher;
import com.mulmeong.utility.application.mapper.LikesDtoMapper;
import com.mulmeong.utility.application.port.in.LikesUseCase;
import com.mulmeong.utility.application.port.in.dto.LikesRenewRequestDto;
import com.mulmeong.utility.application.port.in.dto.LikesRequestDto;
import com.mulmeong.utility.application.port.out.LikesPort;
import com.mulmeong.utility.application.port.out.dto.LikesEntityResponseDto;
import com.mulmeong.utility.common.utils.CursorPage;
import com.mulmeong.utility.domain.model.Likes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class LikesService implements LikesUseCase {

    private final LikesPort likesPort;
    private final LikesDtoMapper likesDtoMapper;
    private final EventPublisher eventPublisher;

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
            eventPublisher.sendLikedEvent(LikeCreateEvent.toDto(likesRequestDto));
        }

    }

    @Override
    public boolean isChecked(LikesRequestDto likesRequestDto) {
        return likesPort.findByMemberAndKind(likesRequestDto)
                .map(LikesEntityResponseDto::isStatus)
                .orElse(false);
    }

    @Override
    public CursorPage<String> getLikes(String memberUuid, String kind, String lastId, int pageSize, int pageNo) {
        return likesPort.getLikes(memberUuid, kind, lastId, pageSize, pageNo);
    }

    @Override
    public void renewValidate(LikesRenewRequestDto requestDto) {
        if (requestDto.getLikeCount() < 10) {
            eventPublisher.sendLikedRenewEvent(LikeRenewCreateEvent.toDto(requestDto));
        }
    }


}

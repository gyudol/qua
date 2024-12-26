package com.mulmeong.utility.application.service;

import com.mulmeong.event.produce.DislikeRenewCreateEvent;
import com.mulmeong.utility.application.EventPublisher;
import com.mulmeong.utility.application.mapper.DislikeDtoMapper;
import com.mulmeong.utility.application.port.in.DislikeUseCase;
import com.mulmeong.utility.application.port.in.dto.DislikeListRequestDto;
import com.mulmeong.utility.application.port.in.dto.DislikeRequestDto;
import com.mulmeong.utility.application.port.in.dto.DislikeRenewRequestDto;
import com.mulmeong.utility.application.port.out.DislikePort;
import com.mulmeong.utility.application.port.out.dto.DislikeEntityResponseDto;
import com.mulmeong.utility.domain.model.Dislike;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class DislikeService implements DislikeUseCase {

    private final DislikePort dislikePort;
    private final DislikeDtoMapper dislikeDtoMapper;
    private final EventPublisher eventPublisher;

    @Override
    public void dislike(DislikeRequestDto dislikeRequestDto) {

        Optional<DislikeEntityResponseDto> existingDislikeDto = dislikePort.findByMemberAndKind(dislikeRequestDto);


        if (existingDislikeDto.isPresent()) {
            // 싫어요가 이미 존재하면 상태를 토글
            Dislike dislike = dislikeDtoMapper.toEntity(existingDislikeDto.get());
            dislike.toggleStatus();
            dislikePort.updateDislike(dislikeDtoMapper.toEntityDto(dislike)); // 업데이트된 리액션 저장
        } else {
            // 싫어요가 존재하지 않으면 새 싫어요 생성
            Dislike newDislike = Dislike.builder()
                    .memberUuid(dislikeRequestDto.getMemberUuid())
                    .kind(dislikeRequestDto.getKind())
                    .kindUuid(dislikeRequestDto.getKindUuid())
                    .status(true) // 기본적으로 싫어요
                    .build();
            dislikePort.saveDislike(dislikeDtoMapper.toDto(newDislike)); // 새 리액션 저장
        }

    }

    @Override
    public boolean isChecked(DislikeRequestDto dislikeRequestDto) {
        return dislikePort.findByMemberAndKind(dislikeRequestDto)
                .map(DislikeEntityResponseDto::isStatus)
                .orElse(false);
    }

    public List<String> getDislikes(DislikeListRequestDto dislikeListRequestDto) {
        List<DislikeEntityResponseDto> dislikedEntities = dislikePort.getByMemberAndKind(dislikeListRequestDto);
        return dislikedEntities.stream()
                .map(DislikeEntityResponseDto::getKindUuid)
                .collect(Collectors.toList());
    }

    @Override
    public void renewValidate(DislikeRenewRequestDto requestDto) {
        if (requestDto.getDislikeCount() < 10) {
            eventPublisher.sendDislikedEvent(DislikeRenewCreateEvent.toDto(requestDto));
        }
    }

}

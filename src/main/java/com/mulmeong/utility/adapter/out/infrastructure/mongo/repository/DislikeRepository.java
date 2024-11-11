package com.mulmeong.utility.adapter.out.infrastructure.mongo.repository;


import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.DislikeEntity;
import com.mulmeong.utility.adapter.out.infrastructure.mongo.mapper.DislikeEntityMapper;
import com.mulmeong.utility.application.port.in.dto.DislikeListRequestDto;
import com.mulmeong.utility.application.port.in.dto.DislikeRequestDto;
import com.mulmeong.utility.application.port.out.DislikePort;
import com.mulmeong.utility.application.port.out.dto.DislikeEntityResponseDto;
import com.mulmeong.utility.application.port.out.dto.DislikeResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
@Slf4j
public class DislikeRepository implements DislikePort {

    private final DislikeMongoRepository dislikeMongoRepository;
    private final DislikeEntityMapper dislikeEntityMapper;

    @Override
    public void saveDislike(DislikeResponseDto dislikeResponseDto) {
        dislikeMongoRepository.save(dislikeEntityMapper.toEntity(dislikeResponseDto));
    }

    @Override
    public void updateDislike(DislikeEntityResponseDto dislikeEntityResponseDto) {
        dislikeMongoRepository.save(dislikeEntityMapper.toUpdate(dislikeEntityResponseDto));
    }

    @Override
    public Optional<DislikeEntityResponseDto> findByMemberAndKind(DislikeRequestDto dislikeRequestDto) {
        DislikeEntity entity = dislikeMongoRepository.findByMemberUuidAndKindAndKindUuid(
                dislikeRequestDto.getMemberUuid(),
                dislikeRequestDto.getKind(),
                dislikeRequestDto.getKindUuid()
        );

        if (entity == null) {
            return Optional.empty();
        }

        return Optional.of(dislikeEntityMapper.toDto(entity));
    }

    @Override
    public List<DislikeEntityResponseDto> getByMemberAndKind(DislikeListRequestDto dislikeListRequestDto) {
        List<DislikeEntity> entities = dislikeMongoRepository.findByMemberUuidAndKindAndStatus(
                dislikeListRequestDto.getMemberUuid(),
                dislikeListRequestDto.getKind(),
                true
        );

        return entities.stream()
                .map(dislikeEntityMapper::toDto)
                .collect(Collectors.toList());
    }


}

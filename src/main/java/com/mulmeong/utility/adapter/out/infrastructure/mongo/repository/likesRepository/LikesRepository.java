package com.mulmeong.utility.adapter.out.infrastructure.mongo.repository.likesRepository;

import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.FeedBookmarkEntity;
import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.LikesEntity;
import com.mulmeong.utility.adapter.out.infrastructure.mongo.mapper.LikesEntityMapper;
import com.mulmeong.utility.application.port.in.dto.LikesListRequestDto;
import com.mulmeong.utility.application.port.in.dto.LikesRequestDto;
import com.mulmeong.utility.application.port.out.LikesPort;
import com.mulmeong.utility.application.port.out.dto.LikesEntityResponseDto;
import com.mulmeong.utility.application.port.out.dto.LikesResponseDto;
import com.mulmeong.utility.common.utils.CursorPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
@Slf4j
public class LikesRepository implements LikesPort {

    private final LikesMongoRepository likesMongoRepository;
    private final LikesEntityMapper likesEntityMapper;

    @Override
    public void saveLikes(LikesResponseDto likesResponseDto) {
        likesMongoRepository.save(likesEntityMapper.toEntity(likesResponseDto));
    }

    @Override
    public void updateLikes(LikesEntityResponseDto likesEntityResponseDto) {
        likesMongoRepository.save(likesEntityMapper.toUpdate(likesEntityResponseDto));
    }

    @Override
    public Optional<LikesEntityResponseDto> findByMemberAndKind(LikesRequestDto likesRequestDto) {
        LikesEntity entity = likesMongoRepository.findByMemberUuidAndKindAndKindUuid(
                likesRequestDto.getMemberUuid(),
                likesRequestDto.getKind(),
                likesRequestDto.getKindUuid()
        );

        if (entity == null) {
            return Optional.empty();
        }

        return Optional.of(likesEntityMapper.toDto(entity));
    }

    @Override
    public CursorPage<String> getLikes(String memberUuid, String kind, String lastId, int pageSize) {
        Pageable pageable = PageRequest.of(0, pageSize, Sort.by(Sort.Direction.DESC, "id"));

        List<LikesEntity> entities;
        if (lastId != null) {
            entities = likesMongoRepository.findByMemberUuidAndKindAndStatusAndIdLessThanOrderByIdDesc(
                    memberUuid, kind, true, lastId, pageable);
        } else {
            entities = likesMongoRepository.findByMemberUuidAndKindAndStatusOrderByIdDesc(
                    memberUuid, kind, true, pageable);
        }

        List<String> kindUuids = entities.stream()
                .map(LikesEntity::getKindUuid)
                .collect(Collectors.toList());

        boolean hasNext = kindUuids.size() == pageSize;
        String nextCursor = hasNext ? entities.get(entities.size() - 1).getId() : null;

        return CursorPage.<String>builder()
                .content(kindUuids)
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .pageSize(pageSize)
                .build();
    }


}

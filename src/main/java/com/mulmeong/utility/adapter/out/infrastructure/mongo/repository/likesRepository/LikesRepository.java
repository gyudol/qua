package com.mulmeong.utility.adapter.out.infrastructure.mongo.repository.likesRepository;

import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.FeedBookmarkEntity;
import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.LikesEntity;
import com.mulmeong.utility.adapter.out.infrastructure.mongo.mapper.LikesEntityMapper;
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
    private final LikesMongoRepositoryCustom likesMongoRepositoryCustom;

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
        return likesMongoRepository.findByMemberUuidAndKindAndKindUuid(
                        likesRequestDto.getMemberUuid(),
                        likesRequestDto.getKind(),
                        likesRequestDto.getKindUuid())
                .map(likesEntityMapper::toDto);
    }

    @Override
    public CursorPage<String> getLikes(
            String memberUuid, String kind, String lastId, int pageSize, int pageNo) {

        CursorPage<LikesEntity> cursorPage = likesMongoRepositoryCustom.getLikesEntity(
                memberUuid, kind, lastId, pageSize, pageNo);

        List<String> kindUuids = cursorPage.getContent().stream()
                .map(LikesEntity::getKindUuid)
                .toList();

        return CursorPage.toCursorPage(cursorPage, kindUuids);
    }


}

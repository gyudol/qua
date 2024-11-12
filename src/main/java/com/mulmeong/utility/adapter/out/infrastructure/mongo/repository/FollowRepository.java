package com.mulmeong.utility.adapter.out.infrastructure.mongo.repository;

import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.FollowEntity;
import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.LikesEntity;
import com.mulmeong.utility.adapter.out.infrastructure.mongo.mapper.FollowEntityMapper;
import com.mulmeong.utility.application.port.in.dto.FollowRequestDto;
import com.mulmeong.utility.application.port.out.FollowPort;
import com.mulmeong.utility.common.exception.BaseException;
import com.mulmeong.utility.common.response.BaseResponseStatus;
import com.mulmeong.utility.common.utils.CursorPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
@Slf4j
public class FollowRepository implements FollowPort {

    private final FollowMongoRepository followMongoRepository;
    private final FollowEntityMapper followEntityMapper;

    @Override
    public boolean existsBySourceUuidAndTargetUuid(FollowRequestDto followRequestDto) {
        return followMongoRepository.existsBySourceUuidAndTargetUuid(followRequestDto.getSourceUuid(), followRequestDto.getTargetUuid());
    }

    @Override
    public void saveFollow(FollowRequestDto followRequestDto) {
        followMongoRepository.save(followEntityMapper.toEntity(followRequestDto));
    }

    @Override
    public void unfollow(FollowRequestDto followRequestDto) {
        followMongoRepository.delete(followMongoRepository.findBySourceUuidAndTargetUuid(followRequestDto.getSourceUuid(), followRequestDto.getTargetUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_EXIST)));
    }

    @Override
    public boolean followStatus(FollowRequestDto followRequestDto) {
        return followMongoRepository.existsBySourceUuidAndTargetUuid(followRequestDto.getSourceUuid(), followRequestDto.getTargetUuid());
    }

    @Override
    public CursorPage<String> getFollowers(String memberUuid, String lastId, int pageSize) {
        Pageable pageable = PageRequest.of(0, pageSize, Sort.by(Sort.Direction.DESC, "id"));

        List<FollowEntity> entities;
        if (lastId != null) {
            entities = followMongoRepository.findByTargetUuidAndIdLessThanOrderByIdDesc(
                    memberUuid, lastId, pageable);
        } else {
            entities = followMongoRepository.findByTargetUuidOrderByIdDesc(
                    memberUuid, pageable);
        }

        List<String> followerUuids = entities.stream()
                .map(FollowEntity::getSourceUuid)
                .collect(Collectors.toList());

        boolean hasNext = followerUuids.size() == pageSize;
        String nextCursor = hasNext ? entities.get(entities.size() - 1).getId() : null;

        return CursorPage.<String>builder()
                .content(followerUuids)
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .pageSize(pageSize)
                .build();
    }
}

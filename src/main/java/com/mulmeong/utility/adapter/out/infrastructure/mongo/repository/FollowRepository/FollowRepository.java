package com.mulmeong.utility.adapter.out.infrastructure.mongo.repository.FollowRepository;

import com.mulmeong.utility.adapter.out.infrastructure.mongo.entity.FeedBookmarkEntity;
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
    private final FollowMongoRepositoryCustom followMongoRepositoryCustom;

    @Override
    public boolean existsBySourceUuidAndTargetUuid(FollowRequestDto followRequestDto) {
        return followMongoRepository.existsBySourceUuidAndTargetUuid(
                followRequestDto.getSourceUuid(), followRequestDto.getTargetUuid());
    }

    @Override
    public void saveFollow(FollowRequestDto followRequestDto) {
        followMongoRepository.save(followEntityMapper.toEntity(followRequestDto));
    }

    @Override
    public void unfollow(FollowRequestDto followRequestDto) {
        followMongoRepository.delete(followMongoRepository.findBySourceUuidAndTargetUuid(
                followRequestDto.getSourceUuid(), followRequestDto.getTargetUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_EXIST)));
    }

    @Override
    public boolean followStatus(FollowRequestDto followRequestDto) {
        return followMongoRepository.existsBySourceUuidAndTargetUuid(
                followRequestDto.getSourceUuid(), followRequestDto.getTargetUuid());
    }

    @Override
    public CursorPage<String> getFollowers(String memberUuid, String lastId, int pageSize, int pageNo) {
        CursorPage<FollowEntity> cursorPage = followMongoRepositoryCustom.getFollowers(
                memberUuid, lastId, pageSize, pageNo);

        List<String> sourceUuid = cursorPage.getContent().stream()
                .map(FollowEntity::getSourceUuid)
                .toList();

        return CursorPage.toCursorPage(cursorPage, sourceUuid);
    }

    @Override
    public CursorPage<String> getFollowings(String memberUuid, String lastId, int pageSize, int pageNo) {
        CursorPage<FollowEntity> cursorPage = followMongoRepositoryCustom.getFollowings(
                memberUuid, lastId, pageSize, pageNo);

        List<String> targetUuid = cursorPage.getContent().stream()
                .map(FollowEntity::getTargetUuid)
                .toList();

        return CursorPage.toCursorPage(cursorPage, targetUuid);
    }
}

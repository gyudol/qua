package com.mulmeong.utility.adapter.out.infrastructure.mongo.repository;

import com.mulmeong.utility.adapter.out.infrastructure.mongo.mapper.FollowEntityMapper;
import com.mulmeong.utility.application.port.in.dto.FollowRequestDto;
import com.mulmeong.utility.application.port.out.FollowPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

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
}

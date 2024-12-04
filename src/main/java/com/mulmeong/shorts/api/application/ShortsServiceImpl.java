package com.mulmeong.shorts.api.application;

import com.mulmeong.shorts.api.dto.in.ShortsCreateDto;
import com.mulmeong.shorts.api.infrastructure.KafkaProducer;
import com.mulmeong.shorts.api.infrastructure.ShortsHashtagRepository;
import com.mulmeong.shorts.api.infrastructure.ShortsMediaRepository;
import com.mulmeong.shorts.api.infrastructure.ShortsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ShortsServiceImpl implements ShortsService {

    private final ShortsRepository shortsRepository;
    private final ShortsMediaRepository shortsMediaRepository;
    private final ShortsHashtagRepository shortsHashtagRepository;
    private final KafkaProducer kafkaProducer;

    @Transactional
    @Override
    public void createShorts(ShortsCreateDto requestDto) {

        shortsRepository.save(requestDto.toShortsEntity());
        shortsMediaRepository.save(requestDto.toShortsMediaEntity());
        shortsHashtagRepository.save(requestDto.toShortsHashtagEntity());
        kafkaProducer.send(requestDto.toEventEntity());
    }

}

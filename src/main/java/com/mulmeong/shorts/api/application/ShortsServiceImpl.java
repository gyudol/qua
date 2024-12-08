package com.mulmeong.shorts.api.application;

import static com.mulmeong.shorts.common.response.BaseResponseStatus.SHORTS_NOT_FOUND;

import com.mulmeong.shorts.api.domain.event.ShortsDeleteEvent;
import com.mulmeong.shorts.api.dto.in.ShortsCreateDto;
import com.mulmeong.shorts.api.dto.in.ShortsHashtagUpdateDto;
import com.mulmeong.shorts.api.dto.in.ShortsInfoUpdateDto;
import com.mulmeong.shorts.api.dto.in.ShortsStatusUpdateDto;
import com.mulmeong.shorts.api.infrastructure.KafkaProducer;
import com.mulmeong.shorts.api.infrastructure.ShortsHashtagRepository;
import com.mulmeong.shorts.api.infrastructure.ShortsMediaRepository;
import com.mulmeong.shorts.api.infrastructure.ShortsRepository;
import com.mulmeong.shorts.common.exception.BaseException;
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

    @Transactional
    @Override
    public void updateShortsInfo(ShortsInfoUpdateDto requestDto) {

        shortsRepository.save(
            requestDto.toShortsEntity(shortsRepository.findByShortsUuid(requestDto.getShortsUuid())
                .orElseThrow(() -> new BaseException(SHORTS_NOT_FOUND))));
        kafkaProducer.send(requestDto.toEventEntity());
    }

    @Transactional
    @Override
    public void updateShortsStatus(ShortsStatusUpdateDto requestDto) {

        shortsRepository.save(
            requestDto.toShortsEntity(shortsRepository.findByShortsUuid(requestDto.getShortsUuid())
                .orElseThrow(() -> new BaseException(SHORTS_NOT_FOUND))));
        kafkaProducer.send(requestDto.toEventEntity());
    }

    @Transactional
    @Override
    public void updateShortsHashtag(ShortsHashtagUpdateDto requestDto) {

        shortsRepository.save(
            requestDto.toShortsEntity(shortsRepository.findByShortsUuid(requestDto.getShortsUuid())
                .orElseThrow(() -> new BaseException(SHORTS_NOT_FOUND))));
        shortsHashtagRepository.save(requestDto.toShortsHashtagEntity());
        kafkaProducer.send(requestDto.toEventEntity());
    }

    @Transactional
    @Override
    public void deleteShorts(String shortsUuid) {

        shortsRepository.delete(shortsRepository.findByShortsUuid(shortsUuid)
            .orElseThrow(() -> new BaseException(SHORTS_NOT_FOUND)));
        shortsHashtagRepository.deleteById(shortsUuid);
        shortsMediaRepository.deleteByShortsUuid(shortsUuid);
        kafkaProducer.send(new ShortsDeleteEvent(shortsUuid));
    }

}

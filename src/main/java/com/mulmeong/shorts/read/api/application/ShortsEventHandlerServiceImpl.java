package com.mulmeong.shorts.read.api.application;

import static com.mulmeong.shorts.read.common.response.BaseResponseStatus.SHORTS_NOT_FOUND;

import com.mulmeong.shorts.read.api.domain.event.ShortsCreateEvent;
import com.mulmeong.shorts.read.api.domain.event.ShortsDeleteEvent;
import com.mulmeong.shorts.read.api.domain.event.ShortsHashtagUpdateEvent;
import com.mulmeong.shorts.read.api.domain.event.ShortsInfoUpdateEvent;
import com.mulmeong.shorts.read.api.domain.event.ShortsStatusUpdateEvent;
import com.mulmeong.shorts.read.api.infrastructure.ShortsEventRepository;
import com.mulmeong.shorts.read.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ShortsEventHandlerServiceImpl implements ShortsEventHandlerService {

    private final ShortsEventRepository shortsEventRepository;

    @Override
    public void createShortsFromEvent(ShortsCreateEvent event) {

        shortsEventRepository.save(event.toDocument());
    }

    @Override
    public void updateShortsHashtagFromEvent(ShortsHashtagUpdateEvent event) {

        shortsEventRepository.save(
            event.toDocument(shortsEventRepository.findByShortsUuid(event.getShortsUuid())
                .orElseThrow(() -> new BaseException(SHORTS_NOT_FOUND))));
    }

    @Override
    public void updateShortsStatusFromEvent(ShortsStatusUpdateEvent event) {

        shortsEventRepository.save(
            event.toDocument(shortsEventRepository.findByShortsUuid(event.getShortsUuid())
                .orElseThrow(() -> new BaseException(SHORTS_NOT_FOUND))));
    }

    @Override
    public void updateShortsInfoFromEvent(ShortsInfoUpdateEvent event) {

        shortsEventRepository.save(
            event.toDocument(shortsEventRepository.findByShortsUuid(event.getShortsUuid())
                .orElseThrow(() -> new BaseException(SHORTS_NOT_FOUND))));
    }

    @Override
    public void deleteShortsFromEvent(ShortsDeleteEvent event) {

        shortsEventRepository.deleteByShortsUuid(event.getShortsUuid());
    }
}

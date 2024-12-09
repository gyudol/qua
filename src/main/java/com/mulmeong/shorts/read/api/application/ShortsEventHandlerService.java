package com.mulmeong.shorts.read.api.application;

import com.mulmeong.shorts.read.api.domain.event.ShortsCreateEvent;
import com.mulmeong.shorts.read.api.domain.event.ShortsDeleteEvent;
import com.mulmeong.shorts.read.api.domain.event.ShortsHashtagUpdateEvent;
import com.mulmeong.shorts.read.api.domain.event.ShortsInfoUpdateEvent;
import com.mulmeong.shorts.read.api.domain.event.ShortsStatusUpdateEvent;

public interface ShortsEventHandlerService {

    void createShortsFromEvent(ShortsCreateEvent event);

    void updateShortsHashtagFromEvent(ShortsHashtagUpdateEvent event);

    void updateShortsStatusFromEvent(ShortsStatusUpdateEvent event);

    void updateShortsInfoFromEvent(ShortsInfoUpdateEvent event);

    void deleteShortsFromEvent(ShortsDeleteEvent event);

}

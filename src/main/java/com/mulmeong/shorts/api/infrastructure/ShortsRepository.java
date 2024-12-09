package com.mulmeong.shorts.api.infrastructure;

import com.mulmeong.shorts.api.domain.entity.Shorts;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortsRepository extends JpaRepository<Shorts, Long> {

    Optional<Shorts> findByShortsUuid(String shortsUuid);

    void deleteByShortsUuid(String shortsUuid);

}

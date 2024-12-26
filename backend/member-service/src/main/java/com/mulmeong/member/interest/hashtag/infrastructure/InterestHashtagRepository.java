package com.mulmeong.member.interest.hashtag.infrastructure;

import com.mulmeong.member.interest.hashtag.domain.InterestHashtag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterestHashtagRepository extends JpaRepository<InterestHashtag, Long> {

    List<InterestHashtag> findByMemberUuid(String memberUuid);

    void deleteAllByMemberUuid(String memberUuid);


}

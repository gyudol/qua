package com.mulmeong.member.interest.category.infrastructure;

import com.mulmeong.member.interest.category.domain.InterestCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterestCategoryRepository extends JpaRepository<InterestCategory, Long> {

    List<InterestCategory> findByMemberUuid(String memberUuid);

    void deleteAllByMemberUuid(String memberUuid);


}

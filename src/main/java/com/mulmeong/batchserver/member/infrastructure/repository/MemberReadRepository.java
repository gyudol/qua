package com.mulmeong.batchserver.member.infrastructure.repository;

import com.mulmeong.batchserver.member.domain.document.MemberRead;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemberReadRepository extends MongoRepository<MemberRead, String> {
    MemberRead findByMemberUuid(String memberUuid);
}

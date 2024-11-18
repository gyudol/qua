package com.mulmeong.member.read.member.infrastructure;

import com.mulmeong.member.read.member.document.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MemberRepository extends MongoRepository<Member, String> {

    Optional<Member> findByNickname(String nickname);
}
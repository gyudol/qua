package com.mulmeong.member.auth.infrastructure;

import com.mulmeong.member.auth.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberUuid(String uuid);

    Optional<Member> findByOauthId(String oauthId);

    Boolean existsMemberByOauthId(String oauthId);
}

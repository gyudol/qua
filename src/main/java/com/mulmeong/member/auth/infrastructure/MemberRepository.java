package com.mulmeong.member.auth.infrastructure;

import com.mulmeong.member.auth.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberUuid(String memberUuid);

    Optional<Member> findByOauthIdAndOauthProvider(String oauthId, String oauthProvider);

    Boolean existsMemberByOauthIdAndOauthProvider(String oauthId, String oauthProvider);

    Boolean existsMemberByNickname(String nickname);

}

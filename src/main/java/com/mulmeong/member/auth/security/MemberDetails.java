package com.mulmeong.member.auth.security;

import com.mulmeong.member.auth.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class MemberDetails implements UserDetails {

    private final Member member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 권한 리스트를 설정할 수 있습니다.
        return List.of();
    }

    @Override
    public String getPassword() {
        return ""; // 비밀번호를 사용하지 않는다면 빈 값 반환
    }

    @Override
    public String getUsername() {
        return member.getEmail(); // 예시로 email을 username으로 반환
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // 비활성화 로직이 필요하다면 수정 가능
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // 비활성화 로직이 필요하다면 수정 가능
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 비활성화 로직이 필요하다면 수정 가능
    }

    @Override
    public boolean isEnabled() {
        return true; // 비활성화 로직이 필요하다면 수정 가능
    }
}
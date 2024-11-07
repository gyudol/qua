package com.mulmeong.member.auth.security;

import com.mulmeong.member.auth.infrastructure.MemberRepository;
import com.mulmeong.member.common.exception.BaseException;
import com.mulmeong.member.common.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String uuid) throws UsernameNotFoundException {
        return new MemberDetails(memberRepository.findByMemberUuid(uuid).orElseThrow(
                () -> new BaseException(BaseResponseStatus.NO_EXIST_USER)
        ));
    }
}


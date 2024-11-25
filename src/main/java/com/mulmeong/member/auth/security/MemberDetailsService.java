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

    /**
     * Spring Security에서 사용자 정보를 가져오는 메서드.
     * .getPrincipal()로 가져올 수 있음.
     *
     * @param memberUuid 회원의 uuid
     * @return 사용자 정보를 담은 UserDetails 객체
     * @throws UsernameNotFoundException 사용자를 찾을 수 없을 때 발생하는 예외
     */
    @Override
    public UserDetails loadUserByUsername(String memberUuid) throws UsernameNotFoundException {
        return new MemberDetails(memberRepository.findByMemberUuid(memberUuid).orElseThrow(
                () -> new BaseException(BaseResponseStatus.NO_EXIST_USER)
        ));
    }
}


package com.mulmeong.member.nickname.application;

import com.mulmeong.member.auth.domain.Member;
import com.mulmeong.member.auth.infrastructure.MemberRepository;
import com.mulmeong.member.common.exception.BaseException;
import com.mulmeong.member.common.response.BaseResponseStatus;
import com.mulmeong.member.nickname.dto.in.UpdateNicknameRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class NicknameServiceImpl implements NicknameService {

    private final MemberRepository memberRepository;

    /**
     * 닉네임 조회.
     *
     * @param memberUuid 회원 uuid
     * @return 닉네임
     */
    @Override
    public String getNickname(String memberUuid) {
        return memberRepository.findByMemberUuid(memberUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_MEMBER))
                .getNickname();
    }

    /**
     * 닉네임 수정.
     *
     * @param requestDto uuid, 닉네임
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateNickname(UpdateNicknameRequestDto requestDto) {

        Member member = memberRepository.findByMemberUuid(requestDto.getMemberUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_MEMBER));

        memberRepository.save(requestDto.toEntity(member));
    }

    /**
     * 닉네임 중복 검사.
     *
     * @param nickname 중복 검사할 닉네임
     * @return 중복 여부(true: 중복 없음, false: 중복 있음)
     */
    @Override
    public boolean checkNickname(String nickname) {

        return !memberRepository.existsMemberByNickname(nickname);
    }
}

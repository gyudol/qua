package com.mulmeong.member.read.member.presentation;

import com.mulmeong.member.read.common.response.BaseResponse;
import com.mulmeong.member.read.member.application.MemberService;
import com.mulmeong.member.read.member.dto.out.MemberProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{nickname}/profile")
    public BaseResponse<MemberProfileDto> getMemberProfile(@PathVariable String nickname) {
        return new BaseResponse<>(memberService.getMemberProfile(nickname));
    }
}

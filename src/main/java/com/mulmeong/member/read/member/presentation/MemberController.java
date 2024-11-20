package com.mulmeong.member.read.member.presentation;

import com.mulmeong.member.read.common.response.BaseResponse;
import com.mulmeong.member.read.member.application.MemberService;
import com.mulmeong.member.read.member.dto.out.MemberProfileDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원 조회 API", description = "회원 조회 API")
@RequestMapping("/v1/members")
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원 프로필 조회", description = "회원 프로필 화면에 필요한 정보와 회원의 포인트, 뱃지 등을 조회합니다.")
    @GetMapping("/{nickname}/profile")
    public BaseResponse<MemberProfileDto> getMemberProfile(@PathVariable String nickname) {
        return new BaseResponse<>(memberService.getMemberProfile(nickname));
    }
}

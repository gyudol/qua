package com.mulmeong.member.read.member.presentation;

import com.mulmeong.member.read.common.response.BaseResponse;
import com.mulmeong.member.read.member.application.MemberService;
import com.mulmeong.member.read.member.dto.out.CompactProfileDto;
import com.mulmeong.member.read.member.dto.out.MemberProfileDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원 Read-DB API", description = "회원 관련한 조회만 담당하는 서비스 ")
@RequestMapping("/v1/members")
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "닉네임으로 회원 프로필 조회", description = "회원 프로필 화면에 필요한 정보를 조회합니다. 있는 거 다 꺼내줍니다.")
    @GetMapping("/{nickname}/profile")
    public BaseResponse<MemberProfileDto> getMemberProfileByNickname(@PathVariable String nickname) {
        return new BaseResponse<>(memberService.getProfileByNickname(nickname));
    }

    @Operation(summary = "회원 UUID로 프로필 조회", description = "회원 프로필 화면에 필요한 정보를 조회합니다. 있는 거 다 꺼내줍니다.")
    @GetMapping("/uuid/{memberUuid}/profile")
    public BaseResponse<MemberProfileDto> getMemberProfileByUuid(@PathVariable String memberUuid) {
        return new BaseResponse<>(memberService.getProfileByMemberUuid(memberUuid));
    }

    @Operation(summary = "회원 UUID로 Compact 프로필 조회", description = "회원 닉네임, 프사, 포인트, 뱃지, 등급 반환. "
            + "피드, 댓글, 쇼츠 등에서 간단한 프로필을 보여줄 때 사용.")

    @GetMapping("/{memberUuid}/compact-profile")
    public BaseResponse<CompactProfileDto> getCompactProfileByUuid(@PathVariable String memberUuid) {
        return new BaseResponse<>(memberService.getCompactProfile(memberUuid));
    }
}
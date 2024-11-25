package com.mulmeong.member.profile.presentation;

import com.mulmeong.member.common.response.BaseResponse;
import com.mulmeong.member.common.response.BaseResponseStatus;
import com.mulmeong.member.profile.application.ProfileService;
import com.mulmeong.member.profile.dto.in.NicknameUpdateRequestDto;
import com.mulmeong.member.profile.dto.in.ProfileImgUpdateRequestDto;
import com.mulmeong.member.profile.vo.in.NicknameVo;
import com.mulmeong.member.profile.vo.in.ProfileImgVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.mulmeong.member.common.response.BaseResponseStatus.EXISTS_NICKNAME;

@Tag(name = "프로필", description = "회원 정보 관련 API(마이페이지)")
@RequestMapping("/v1/members")
@RequiredArgsConstructor
@RestController
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/{memberUuid}/nickname")
    @Operation(summary = "닉네임 조회", description = "회원 Uuid로 닉네임 조회 API")
    public BaseResponse<String> getNickname(@PathVariable String memberUuid) {

        return new BaseResponse<>(profileService.getNickname(memberUuid));
    }

    @PutMapping("/{memberUuid}/nickname")
    @Operation(summary = "닉네임 수정", description = """
            회원의 닉네임을 수정하는 API입니다.
            닉네임은 영문, 숫자, 한글, #만 포함할 수 있으며, 3자 이상 15자 이하이어야 합니다.
            """)
    public BaseResponse<Void> updateNickname(
            @PathVariable String memberUuid,
            @RequestBody @Valid NicknameVo nicknameVo) {

        profileService.updateNickname(NicknameUpdateRequestDto.toDto(
                memberUuid, nicknameVo.getNickname()));

        return new BaseResponse<>();
    }

    @PostMapping("/check-nickname")
    @Operation(summary = "닉네임 중복 검사", description = """
            회원가입 전 닉네임 중복 검사 API입니다.
            변경 가능시 : 200(메시지는 result) / 변경 불가 : 409(메시지 in message)
            닉네임은 영문, 숫자, 한글, #만 포함할 수 있으며, 3자 이상 15자 이하이어야 합니다.
            """)
    @ApiResponse(responseCode = "200", description = "SUCCESS(성공)")
    @ApiResponse(responseCode = "409", description = "CONFLICT(이미 존재하는 닉네임입니다")
    public BaseResponse<Object> checkNickname(
            @RequestBody @Valid NicknameVo nicknameVo) {

        if (profileService.checkNickname(nicknameVo.getNickname())) {
            return new BaseResponse<>("사용 가능한 닉네임입니다.");
        } else {
            return new BaseResponse<>(EXISTS_NICKNAME);
        }
    }

    @PutMapping("/{memberUuid}/profile-img")
    @Operation(summary = "프로필 이미지 수정", description = "프로필 이미지 수정 API, URL은 2083자 이하")
    public BaseResponse<Void> updateProfileImage(
            @PathVariable String memberUuid,
            @RequestBody @Valid ProfileImgVo profileImgVo) {

        profileService.updateProfileImage(ProfileImgUpdateRequestDto.toDto(
                memberUuid, profileImgVo.getProfileImgUrl()));

        return new BaseResponse<>();
    }


}

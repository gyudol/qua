package com.mulmeong.member.nickname.presentation;

import com.mulmeong.member.common.response.BaseResponse;
import com.mulmeong.member.common.response.BaseResponseStatus;
import com.mulmeong.member.nickname.application.NicknameService;
import com.mulmeong.member.nickname.dto.in.UpdateNicknameRequestDto;
import com.mulmeong.member.nickname.vo.in.NicknameVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "닉네임", description = "닉네임 관련 API")
@RequestMapping("/v1/members")
@RequiredArgsConstructor
@RestController
public class NicknameController {

    private final NicknameService nicknameService;

    @GetMapping("/{memberUuid}/nickname")
    @Operation(summary = "닉네임 조회", description = "닉네임 조회 API")
    public BaseResponse<String> getNickname(@PathVariable String memberUuid) {

        return new BaseResponse<>(nicknameService.getNickname(memberUuid));
    }

    @PutMapping("/{memberUuid}/nickname")
    @Operation(summary = "닉네임 수정", description = "닉네임 수정 API")
    public BaseResponse<Void> updateNickname(
            @PathVariable String memberUuid,
            @RequestBody @Valid NicknameVo nicknameVo) {

        nicknameService.updateNickname(UpdateNicknameRequestDto.toDto(
                memberUuid, nicknameVo.getNickname()));

        return new BaseResponse<>();
    }

    @PostMapping("/check-nickname")
    @Operation(summary = "닉네임 중복 검사", description = " 변경 가능 : 200(메시지 in result) / 변경 불가 : 409(메시지 in message)")
    @ApiResponse(responseCode = "200", description = "SUCCESS(성공)")
    @ApiResponse(responseCode = "409", description = "CONFLICT(이미 존재하는 닉네임입니다")
    public BaseResponse<Object> checkNickname(
            @RequestBody @Valid NicknameVo nicknameVo) {

        if (nicknameService.checkNickname(nicknameVo.getNickname())) {
            return new BaseResponse<>("사용 가능한 닉네임입니다.");
        } else {
            return new BaseResponse<>(BaseResponseStatus.EXISTS_NICKNAME);
        }
    }

}

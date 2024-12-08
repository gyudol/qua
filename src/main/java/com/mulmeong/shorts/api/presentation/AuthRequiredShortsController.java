package com.mulmeong.shorts.api.presentation;

import com.mulmeong.shorts.api.application.ShortsService;
import com.mulmeong.shorts.api.dto.in.ShortsCreateDto;
import com.mulmeong.shorts.api.dto.in.ShortsInfoUpdateDto;
import com.mulmeong.shorts.api.vo.in.ShortsCreateVo;
import com.mulmeong.shorts.api.vo.in.ShortsInfoUpdateVo;
import com.mulmeong.shorts.common.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Shorts")
@RequiredArgsConstructor
@RequestMapping("/auth/v1/shorts")
@RestController
public class AuthRequiredShortsController {

    private final ShortsService shortsService;

    @Operation(summary = "Shorts 생성 API", description = """
        - Visibility: `VISIBLE` / `HIDDEN` / `REPORTED`<br>
        - MediaType: `IMAGE` / `VIDEO_THUMBNAIL` / `VIDEO_360` / `VIDEO_540` / `VIDEO_720` / `VIDEO_MP4`<br><br>
        - playtime: second 기준으로 입력 ex) 90 sec => 90 저장
        - ShortsMedia 테이블은 **FE에서 생성한 MediaUUID를 기본키로 설정**하는 것에 주의""")
    @PostMapping
    public BaseResponse<Void> createShorts(@RequestBody @Valid ShortsCreateVo requestVo) {

        shortsService.createShorts(ShortsCreateDto.toDto(requestVo));
        return new BaseResponse<>();
    }

    @Operation(summary = "Shorts 정보 수정 API")
    @PutMapping("/{shortsUuid}")
    public BaseResponse<Void> updateShortsInfo(
        @PathVariable("shortsUuid") String shortsUuid, @RequestBody ShortsInfoUpdateVo requestVo) {

        shortsService.updateShortsInfo(ShortsInfoUpdateDto.toDto(shortsUuid, requestVo));
        return new BaseResponse<>();
    }

}

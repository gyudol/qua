package com.mulmeong.shorts.read.api.presentation;

import com.mulmeong.shorts.read.api.application.ShortsQueryService;
import com.mulmeong.shorts.read.api.dto.out.ShortsResponseDto;
import com.mulmeong.shorts.read.common.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Shorts Query")
@RequiredArgsConstructor
@RequestMapping("/v1/shorts")
@RestController
public class ShortsQueryController {

    private final ShortsQueryService shortsQueryService;

    @Operation(summary = "Shorts 단건 정보 조회 API", description = """
        - Visibility: `VISIBLE` / `HIDDEN` / `REPORTED`<br>
        - MediaType: `VIDEO_THUMBNAIL` / `VIDEO_360` / `VIDEO_540` / `VIDEO_720` / `VIDEO_MP4`<br><br>
        - playtime: second 기준으로 입력 ex) 90 sec => 90 저장 **(Range: 0 ~ 32767)**
        - ShortsMedia 테이블은 **FE에서 생성한 MediaUUID를 기본키로 설정**하는 것에 주의""")
    @GetMapping("/{shortsUuid}")
    public BaseResponse<ShortsResponseDto> getSingleShorts(@PathVariable String shortsUuid) {

        return new BaseResponse<>(shortsQueryService.getSingleShorts(shortsUuid));
    }

}

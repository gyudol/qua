package com.mulmeong.feed.read.api.presentation;

import com.mulmeong.feed.read.api.application.FeedSearchService;
import com.mulmeong.feed.read.api.dto.in.FeedSearchRequestDto;
import com.mulmeong.feed.read.api.dto.in.IndexSyncRequestDto;
import com.mulmeong.feed.read.api.dto.out.FeedResponseDto;
import com.mulmeong.feed.read.api.vo.in.IndexSyncRequestVo;
import com.mulmeong.feed.read.common.response.BaseResponse;
import com.mulmeong.feed.read.common.utils.CursorPage;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Feed Search")
@RequiredArgsConstructor
@RequestMapping("/v1")
@RestController
public class FeedSearchController {

    private final FeedSearchService feedSearchService;

    @Operation(summary = "Feed 검색 API", description = """ 
        - `keyword`와 유사한 피드 목록 조회<br><br>
        - 한글·영어 keyword 모두 가능<br><br>
        - `title`, `content`, `categoryName`, `hashtags` 유사도 측정<br><br>
        - 가중치 기반의 `netLikes`, `createdAt` 내림차순<br><br>
        - `nextCursor` 변수를 반환하지만 내부 로직에서 사용하지 않음""")
    @GetMapping("/search/{keyword}/feeds")
    public BaseResponse<CursorPage<FeedResponseDto>> searchFeeds(
        @PathVariable String keyword,
        @RequestParam(required = false) Integer pageSize,
        @RequestParam(required = false) Integer pageNo) {

        return new BaseResponse<>(
            feedSearchService.searchFeeds(
                FeedSearchRequestDto.toDto(keyword, pageSize, pageNo)));
    }

    @Hidden
    @PostMapping("/feeds/indexing")
    public BaseResponse<Void> syncIndex(@RequestBody IndexSyncRequestVo requestVo) {

        feedSearchService.syncIndex(IndexSyncRequestDto.toDto(requestVo));
        return new BaseResponse<>();
    }

}

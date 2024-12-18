package com.mulmeong.feed.read.api.application;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.json.JsonData;
import com.mulmeong.feed.read.api.domain.document.ElasticFeed;
import com.mulmeong.feed.read.api.dto.client.CategoryDto;
import com.mulmeong.feed.read.api.dto.client.HashtagDto;
import com.mulmeong.feed.read.api.dto.in.FeedRecommendRequestDto;
import com.mulmeong.feed.read.api.dto.out.FeedResponseDto;
import com.mulmeong.feed.read.common.utils.CursorPage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class FeedRecommendServiceImpl implements FeedRecommendService {

    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final int DEFAULT_PAGE_NUMBER = 0;

    private final ElasticsearchOperations elasticsearchOperations;
    private final FeignService feignService;

    @Transactional(readOnly = true)
    public CursorPage<FeedResponseDto> recommendFeeds(FeedRecommendRequestDto requestDto) {

        String interestCategory = Optional.ofNullable(
                feignService.getInterestCategory(requestDto.getMemberUuid()))
            .filter(list -> !list.isEmpty())
            .map(list -> list.get(0))
            .map(CategoryDto::getId)
            .map(categoryId -> feignService.getCategory(categoryId).getCategoryName())
            .orElse("일상");
        String interestHashtag = Optional.ofNullable(
                feignService.getInterestHashtag(requestDto.getMemberUuid()))
            .filter(list -> !list.isEmpty())
            .map(list -> list.get(0))
            .map(HashtagDto::getName)
            .orElse("물");
        List<String> followingList = feignService.getAllFollowings(requestDto.getMemberUuid());

        log.info("Interest Category: {}", interestCategory);
        log.info("Interest Hashtag: {}", interestHashtag);
        log.info("Following List: {}", String.join(", ", followingList));

        List<Query> shouldQueries = new ArrayList<>();

        Optional.ofNullable(interestCategory).ifPresent(category -> {
            shouldQueries.add(new Query.Builder()
                .match(m -> m
                    .field("title")
                    .query(category)
                    .boost(1.2F)
                    .fuzziness("AUTO"))
                .build());

            shouldQueries.add(new Query.Builder()
                .match(m -> m
                    .field("content")
                    .query(category)
                    .boost(1.2F)
                    .fuzziness("AUTO"))
                .build());

            shouldQueries.add(new Query.Builder()
                .term(m -> m
                    .field("categoryName")
                    .value(category)
                    .boost(2.0F))
                .build());

            shouldQueries.add(new Query.Builder()
                .match(m -> m
                    .field("hashtags.name")
                    .query(category)
                    .boost(1.2F)
                    .fuzziness("AUTO"))
                .build());
        });

        Optional.ofNullable(interestHashtag).ifPresent(hashtag -> {
            shouldQueries.add(new Query.Builder()
                .match(m -> m
                    .field("title")
                    .query(hashtag)
                    .boost(1.2F)
                    .fuzziness("AUTO"))
                .build());

            shouldQueries.add(new Query.Builder()
                .match(m -> m
                    .field("content")
                    .query(hashtag)
                    .boost(1.2F)
                    .fuzziness("AUTO"))
                .build());

            shouldQueries.add(new Query.Builder()
                .match(m -> m
                    .field("categoryName")
                    .query(hashtag)
                    .boost(1.2F)
                    .fuzziness("AUTO"))
                .build());

            shouldQueries.add(new Query.Builder()
                .match(m -> m
                    .field("hashtags.name")
                    .query(hashtag)
                    .boost(1.6F)
                    .fuzziness("AUTO"))
                .build());
        });

        // 2. Boost based on memberUuid in followingList
        if (followingList != null && !followingList.isEmpty()) {
            for (String memberUuid : followingList) {
                shouldQueries.add(new Query.Builder()
                    .term(m -> m
                        .field("memberUuid")
                        .value(memberUuid)
                        .boost(4.0F) // Boost for following member
                    )
                    .build());
            }
        }

        shouldQueries.add(new Query.Builder()
            .range(r -> r
                .field("netLikes")
                .gte(JsonData.of(5)) // Filter for popular content with netLikes >= 5
                .boost(2.0F) // Boost popular content by 2x
            )
            .build());

        Query query = new Query.Builder()
            .bool(new BoolQuery.Builder().should(shouldQueries).build())
            .build();

        int curPageNo = Optional.ofNullable(requestDto.getPageNo())
            .map(pageNo -> pageNo > 0 ? pageNo - 1 : 0).orElse(DEFAULT_PAGE_NUMBER);
        int curPageSize = Optional.ofNullable(requestDto.getPageSize()).orElse(DEFAULT_PAGE_SIZE);

        NativeQuery searchQuery = NativeQuery.builder()
            .withQuery(query)
            .withSort(Sort.by(Sort.Direction.DESC, "_score"))
            .withPageable(PageRequest.of(curPageNo, curPageSize + 1))
            .build();

        SearchHits<ElasticFeed> searchHits = elasticsearchOperations.search(searchQuery,
            ElasticFeed.class);

        for (SearchHit<ElasticFeed> searchHit : searchHits.getSearchHits()) {
            log.info("Document ID: {}, feedUuid: {}, Score: {}", searchHit.getId(),
                searchHit.getContent().getFeedUuid(), searchHit.getScore());
        }

        //        long totalHits = searchHits.getTotalHits();
        String nextCursor = null;
        boolean hasNext = false;

        // used for subList
        List<SearchHit<ElasticFeed>> searchHitList = searchHits.stream().toList();

        if (searchHits.getSearchHits().size() > curPageSize) {
            hasNext = true;
            nextCursor = searchHits.getSearchHit(curPageSize).getContent().getId();
            searchHitList = searchHitList.subList(0, curPageSize);
        }

        return new CursorPage<>(searchHitList.stream().map(SearchHit::getContent)
            .map(FeedResponseDto::fromDocument).toList(),
            nextCursor, hasNext, searchHitList.size(), curPageNo + 1);
    }
}

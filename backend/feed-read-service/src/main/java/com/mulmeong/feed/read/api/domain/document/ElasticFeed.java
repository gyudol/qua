package com.mulmeong.feed.read.api.domain.document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mulmeong.feed.read.api.domain.model.Hashtag;
import com.mulmeong.feed.read.api.domain.model.Media;
import com.mulmeong.feed.read.api.domain.model.Visibility;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Getter
@Document(indexName = "feed")
@Setting(replicas = 0)
@ToString
public class ElasticFeed {

    @Id
    private String id;

    @Field(type = FieldType.Keyword, index = false, docValues = false)
    private String feedUuid;

    @Field(type = FieldType.Keyword, index = false, docValues = false)
    private String memberUuid;

    @Field(type = FieldType.Text, analyzer = "nori_analyzer", searchAnalyzer = "nori_analyzer")
    private String title;

    @Field(type = FieldType.Text, analyzer = "nori_analyzer", searchAnalyzer = "nori_analyzer")
    private String content;

    @Field(type = FieldType.Text, analyzer = "nori_analyzer", searchAnalyzer = "nori_analyzer")
    private String categoryName;

    @Field(type = FieldType.Text, docValues = false)
    private Visibility visibility;

    @Field(type = FieldType.Object, analyzer = "nori_analyzer", searchAnalyzer = "nori_analyzer")
    private List<Hashtag> hashtags;

    @Field(type = FieldType.Object, index = false, docValues = false)
    private List<Media> mediaList;

    @Field(type = FieldType.Long)
    private Long likeCount;

    @Field(type = FieldType.Long)
    private Long dislikeCount;

    @Field(type = FieldType.Long)
    private Long netLikes;  // Calculated as likeCount - dislikeCount

    @Field(type = FieldType.Long)
    private Long commentCount;

    @Field(type = FieldType.Date)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX||"
                                                           + "yyyy-MM-dd'T'HH:mm:ss.SSSX||"
                                                           + "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private ZonedDateTime createdAt;

    @Field(type = FieldType.Date)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX||"
                                                           + "yyyy-MM-dd'T'HH:mm:ss.SSSX||"
                                                           + "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private ZonedDateTime updatedAt;

    public static ElasticFeed toElasticDocument(Feed feed) {
        return ElasticFeed.builder()
            .id(feed.getId())
            .feedUuid(feed.getFeedUuid())
            .memberUuid(feed.getMemberUuid())
            .title(feed.getTitle())
            .content(feed.getContent())
            .visibility(feed.getVisibility())
            .categoryName(feed.getCategoryName())
            .hashtags(feed.getHashtags())
            .mediaList(feed.getMediaList())
            .likeCount(feed.getLikeCount())
            .dislikeCount(feed.getDislikeCount())
            .netLikes(feed.getNetLikes())
            .commentCount(feed.getCommentCount())
            .createdAt(feed.getCreatedAt().atZone(ZoneId.of("Asia/Seoul")))
            .updatedAt(feed.getUpdatedAt().atZone(ZoneId.of("Asia/Seoul")))
            .build();
    }

    @Builder
    public ElasticFeed(String id, String feedUuid, String memberUuid, String title, String content,
        String categoryName, Visibility visibility, List<Hashtag> hashtags, List<Media> mediaList,
        Long likeCount, Long dislikeCount, Long netLikes, Long commentCount,
        ZonedDateTime createdAt, ZonedDateTime updatedAt) {

        this.id = id;
        this.feedUuid = feedUuid;
        this.memberUuid = memberUuid;
        this.title = title;
        this.content = content;
        this.visibility = visibility;
        this.categoryName = categoryName;
        this.hashtags = hashtags;
        this.mediaList = mediaList;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.netLikes = netLikes;
        this.commentCount = commentCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}

package com.mulmeong.feed.read.api.domain.document;

import com.mulmeong.feed.read.api.domain.model.Hashtag;
import com.mulmeong.feed.read.api.domain.model.Media;
import com.mulmeong.feed.read.api.domain.model.Visibility;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
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
    private String createdAt;

    @Field(type = FieldType.Date)
    private String updatedAt;

    @Builder
    public ElasticFeed(String id, String feedUuid, String memberUuid, String title, String content,
        String categoryName, Visibility visibility, List<Hashtag> hashtags, List<Media> mediaList,
        Long likeCount, Long dislikeCount, Long netLikes, Long commentCount,
        String createdAt, String updatedAt) {

        this.id = id;
        this.feedUuid = feedUuid;
        this.memberUuid = memberUuid;
        this.title = title;
        this.content = content;
        this.visibility = visibility;
        this.categoryName = categoryName;
        this.hashtags = hashtags;
        this.mediaList = mediaList;
        this.dislikeCount = dislikeCount;
        this.likeCount = likeCount;
        this.netLikes = netLikes;
        this.createdAt = createdAt;
        this.commentCount = commentCount;
        this.updatedAt = updatedAt;
    }
}

package com.mulmeong.shorts.read.api.domain.document;

import com.mulmeong.shorts.read.api.domain.model.Hashtag;
import com.mulmeong.shorts.read.api.domain.model.Media;
import com.mulmeong.shorts.read.api.domain.model.Visibility;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@Document(collection = "shorts")
public class Shorts {

    @Id
    private String id;
    private String shortsUuid;
    private String memberUuid;
    private String title;
    private Short playtime;
    private Visibility visibility;
    private List<Hashtag> hashtags;
    private Media media;
    private Long likeCount;
    private Long dislikeCount;
    private Long netLikes;  // likesCount - dislikeCount
    private Long commentCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public Shorts(String id, String shortsUuid, String memberUuid, String title, Short playtime,
        Visibility visibility, List<Hashtag> hashtags, Media media, Long likeCount,
        Long dislikeCount, Long netLikes, Long commentCount, LocalDateTime createdAt,
        LocalDateTime updatedAt) {

        this.id = id;
        this.shortsUuid = shortsUuid;
        this.memberUuid = memberUuid;
        this.title = title;
        this.playtime = playtime;
        this.visibility = visibility;
        this.hashtags = hashtags;
        this.media = media;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.netLikes = netLikes;
        this.commentCount = commentCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}

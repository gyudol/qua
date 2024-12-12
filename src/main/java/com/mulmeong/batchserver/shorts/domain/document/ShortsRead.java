package com.mulmeong.batchserver.shorts.domain.document;


import com.mulmeong.batchserver.shorts.domain.model.Hashtag;
import com.mulmeong.batchserver.shorts.domain.model.Media;
import com.mulmeong.batchserver.shorts.domain.model.Visibility;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@Document(collection = "shorts")
public class ShortsRead {

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
    public ShortsRead(String id, String shortsUuid, String memberUuid, String title, Short playtime,
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

package com.mulmeong.contest.read.domain.document;

import com.mulmeong.contest.read.domain.model.Media;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "contest_post")
public class ContestPost {

    @Id
    private String id;
    private String postUuid;
    private Long contestId;
    private String memberUuid;
    private Media media;
    private LocalDateTime createdAt;
    private Integer voteCount;


}

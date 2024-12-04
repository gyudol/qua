package com.mulmeong.contest.read.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "contest_vote_record")
public class ContestVote {

    @Id
    private String id;
    private Long contestId;
    private String postUuid;
    private String memberUuid;

}
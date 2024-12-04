package com.mulmeong.contest.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QContestPost is a Querydsl query type for ContestPost
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QContestPost extends EntityPathBase<ContestPost> {

    private static final long serialVersionUID = 542846456L;

    public static final QContestPost contestPost = new QContestPost("contestPost");

    public final NumberPath<Long> contestId = createNumber("contestId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<MediaType> mediaType = createEnum("mediaType", MediaType.class);

    public final StringPath mediaUrl = createString("mediaUrl");

    public final StringPath memberUuid = createString("memberUuid");

    public final StringPath postUuid = createString("postUuid");

    public QContestPost(String variable) {
        super(ContestPost.class, forVariable(variable));
    }

    public QContestPost(Path<? extends ContestPost> path) {
        super(path.getType(), path.getMetadata());
    }

    public QContestPost(PathMetadata metadata) {
        super(ContestPost.class, metadata);
    }

}


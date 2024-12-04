package com.mulmeong.contest.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QContestResult is a Querydsl query type for ContestResult
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QContestResult extends EntityPathBase<ContestResult> {

    private static final long serialVersionUID = 2032428917L;

    public static final QContestResult contestResult = new QContestResult("contestResult");

    public final NumberPath<Long> badgeId = createNumber("badgeId", Long.class);

    public final NumberPath<Long> contestId = createNumber("contestId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath memberUuid = createString("memberUuid");

    public final StringPath postUuid = createString("postUuid");

    public final NumberPath<Byte> ranking = createNumber("ranking", Byte.class);

    public final NumberPath<Long> voteCount = createNumber("voteCount", Long.class);

    public QContestResult(String variable) {
        super(ContestResult.class, forVariable(variable));
    }

    public QContestResult(Path<? extends ContestResult> path) {
        super(path.getType(), path.getMetadata());
    }

    public QContestResult(PathMetadata metadata) {
        super(ContestResult.class, metadata);
    }

}


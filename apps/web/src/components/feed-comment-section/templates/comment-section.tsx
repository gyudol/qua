"use client";

import React from "react";
import {
  useGetFeedCommentsInfiniteQuery,
  usePostFeedCommentQuery,
} from "@/hooks/comment-service";
import { CommentThread } from "../organisms";
import { CommentInput } from "../atoms";

interface CommentSectionProps {
  feedUuid: string;
}

export function CommentSection({ feedUuid }: CommentSectionProps) {
  const {
    data,
    hasNextPage: _1,
    fetchNextPage: _2,
    status: _3,
  } = useGetFeedCommentsInfiniteQuery({ feedUuid });

  const { data: newCommentList } = usePostFeedCommentQuery({ feedUuid });

  return (
    <section>
      <CommentInput {...{ feedUuid }} />
      {newCommentList?.map((comment) => (
        <CommentThread key={comment.commentUuid} {...comment} />
      ))}
      {data?.pages.map((page) => (
        <React.Fragment key={page.pageNo}>
          {page.content.map((comment) => (
            <CommentThread key={comment.commentUuid} {...comment} />
          ))}
        </React.Fragment>
      ))}
    </section>
  );
}

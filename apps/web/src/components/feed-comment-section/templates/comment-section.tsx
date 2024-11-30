"use client";

import React from "react";
import {
  useGetFeedCommentsInfiniteQuery,
  usePostFeedCommentQuery,
} from "@/hooks/comment-service";
import { useInfiniteScroll } from "@/hooks/useInfiniteScroll";
import { CommentThread } from "../organisms";
import { CommentInput } from "../atoms";

interface CommentSectionProps {
  feedUuid: string;
}

export function CommentSection({ feedUuid }: CommentSectionProps) {
  const { data, hasNextPage, fetchNextPage, isFetchingNextPage } =
    useGetFeedCommentsInfiniteQuery({ feedUuid });
  const { data: newCommentList } = usePostFeedCommentQuery({ feedUuid });
  const observerRef = useInfiniteScroll({
    hasNextPage,
    fetchNextPage,
    isFetchingNextPage,
  });

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
      <div ref={observerRef}>
        {isFetchingNextPage ? <p>loading more...</p> : null}
      </div>
    </section>
  );
}

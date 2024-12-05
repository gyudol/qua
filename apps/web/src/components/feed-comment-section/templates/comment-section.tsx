"use client";

import React from "react";
import {
  useGetFeedCommentsInfiniteQuery,
  usePostFeedCommentQuery,
} from "@/hooks/comment-service";
import { useInfiniteScroll } from "@/hooks/useInfiniteScroll";
import { CommentInput } from "../atoms";
import { CommentView } from "../organisms";

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
    <section id="comment">
      <CommentInput {...{ feedUuid }} />
      {newCommentList?.map((comment) => (
        <CommentView key={comment.commentUuid} {...comment} />
      ))}
      {data?.pages.map((page) => (
        <React.Fragment key={page.pageNo}>
          {page.content.map((comment) => (
            <CommentView key={comment.commentUuid} {...comment} />
          ))}
        </React.Fragment>
      ))}
      <div ref={observerRef}>
        {isFetchingNextPage ? <p>loading more...</p> : null}
      </div>
    </section>
  );
}

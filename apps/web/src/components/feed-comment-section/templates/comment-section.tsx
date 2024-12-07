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
    <section id="comment" className="flex flex-col min-h-[50vh] p-[1rem]">
      <div className="mb-[1rem]">
        <CommentInput {...{ feedUuid }} />
      </div>
      <div>
        {newCommentList?.map((comment) => (
          <CommentView key={comment.commentUuid} {...comment} justNow />
        ))}
        {data?.pages.map((page) => (
          <React.Fragment key={page.pageNo}>
            {page.content.map((comment) => (
              <CommentView key={comment.commentUuid} {...comment} />
            ))}
          </React.Fragment>
        ))}
      </div>
      <div ref={observerRef}>
        {isFetchingNextPage ? <p>loading more...</p> : null}
      </div>
    </section>
  );
}

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
    <section
      id="comment"
      className="flex flex-col p-[1rem] overflow-auto relative min-h-[10rem]"
    >
      {/* <div className="fixed w-full top-5 left-0 py-4 px-4 bg-white z-[100]"> */}
      <div className="w-full py-[1rem] bg-white">
        <CommentInput {...{ feedUuid }} />
      </div>
      {newCommentList?.length || data?.pages[0].content.length ? (
        <div>
          {newCommentList?.map((comment) => (
            <CommentView key={comment.commentUuid} {...comment} justNow />
          ))}
          {data?.pages.map((page) =>
            page.content.map((comment) => (
              <CommentView key={comment.commentUuid} {...comment} />
            )),
          )}
        </div>
      ) : (
        <div className="w-full flex-1 flex justify-center items-center text-teal-400 text-sm">
          첫번째 댓글을 작성해보세요!
        </div>
      )}

      <div ref={observerRef}>
        {isFetchingNextPage ? <p>loading more...</p> : null}
      </div>
    </section>
  );
}

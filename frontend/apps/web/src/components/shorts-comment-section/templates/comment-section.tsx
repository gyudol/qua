"use client";

import React from "react";
import {
  useGetShortsCommentsInfiniteQuery,
  usePostShortsCommentQuery,
} from "@/hooks/comment-service";
import { useInfiniteScroll } from "@/hooks/useInfiniteScroll";
import { CommentInput } from "../atoms";
import { CommentView } from "../organisms";

interface CommentSectionProps {
  shortsUuid: string;
}

export function CommentSection({ shortsUuid }: CommentSectionProps) {
  const { data, hasNextPage, fetchNextPage, isFetchingNextPage } =
    useGetShortsCommentsInfiniteQuery({ shortsUuid });
  const { data: newCommentList } = usePostShortsCommentQuery({ shortsUuid });
  const observerRef = useInfiniteScroll({
    hasNextPage,
    fetchNextPage,
    isFetchingNextPage,
  });

  return (
    <section
      id="comment"
      className="flex flex-col m-[1rem] overflow-auto relative min-h-[10rem]"
    >
      <div className="fixed w-full top-5 left-0 my-4 px-4 bg-white z-[100]">
        <CommentInput {...{ shortsUuid }} />
      </div>
      {newCommentList?.length || data?.pages[0].content.length ? (
        <div className="mt-[4rem]">
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

"use client";

import React from "react";
import { useInfiniteScroll } from "@/hooks";
import { useGetConstestListInfiniteQuery } from "@/hooks/contest-list-service";
import ContestCard from "../organisms/ContestListCard";

export default function ContestList({ contestId }: { contestId: number }) {
  const { data, hasNextPage, fetchNextPage, isFetchingNextPage } =
    useGetConstestListInfiniteQuery({ contestId, pageSize: 8, pageNo: 1 });
  const observerRef = useInfiniteScroll({
    hasNextPage,
    fetchNextPage,
    isFetchingNextPage,
  });
  // console.log("데이터 ", data?.pages.content);
  // console.log("순회 데이터", data?.pages);
  // console.log("콘테스트 id", contestId);

  return (
    <div>
      {data
        ? data.pages.map((page) =>
            page.content.map(
              ({ media, createdAt, voteCount, postUuid, memberUuid }) => (
                <ContestCard
                  key={postUuid}
                  {...{
                    contestId,
                    media,
                    createdAt,
                    voteCount,
                    memberUuid,
                    postUuid,
                  }}
                />
              ),
            ),
          )
        : null}
      <div ref={observerRef} className="">
        {isFetchingNextPage ? "로딩중" : null}
      </div>
    </div>
  );
}

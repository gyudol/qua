"use client";

import React, { useState } from "react";
import { useInfiniteScroll } from "@/hooks";
import { useGetConstestListInfiniteQuery } from "@/hooks/contest-list-service";
import ContestCard from "../organisms/ContestListCard";
import ContestFilter from "../atoms/ContestFilter";

export default function ContestList({
  contestId,
  sortBy,
}: {
  contestId: number;
  sortBy?: "LATEST" | "VOTES";
}) {
  const [sort, setSort] = useState(sortBy);
  const { data, hasNextPage, fetchNextPage, isFetchingNextPage } =
    useGetConstestListInfiniteQuery({
      contestId,
      pageSize: 80,
      pageNo: 1,
      sortBy: sort,
    });
  const observerRef = useInfiniteScroll({
    hasNextPage,
    fetchNextPage,
    isFetchingNextPage,
  });

  return (
    <div className="min-h-screen bg-gray-100 pt-5 px-4 border-2">
      <ContestFilter {...{ setSort }} />
      <div className="grid grid-cols-2 gap-4 w-full">
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
    </div>
  );
}

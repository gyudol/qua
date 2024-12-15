"use client";

import React from "react";
import { useInfiniteScroll } from "@/hooks";
import { useGetConstestHistoryInfiniteQuery } from "@/hooks/contest-service";
import ContestHistory from "../organisms/ContestHistory";

export default function ContestHistoryMain() {
  const { data, hasNextPage, fetchNextPage, isFetchingNextPage } =
    useGetConstestHistoryInfiniteQuery({});
  const observerRef = useInfiniteScroll({
    hasNextPage,
    fetchNextPage,
    isFetchingNextPage,
  });
  // console.log("1", data?.pages);

  return (
    <div>
      {data
        ? data.pages.map((page) =>
            page.content.map(
              ({
                contestId,
                endDate,
                startDate,
                imgUrl,
                winners,
                contestName,
              }) => (
                <ContestHistory
                  key={contestId}
                  {...{ winners, imgUrl, endDate, startDate, contestName }}
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

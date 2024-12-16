"use client";

import React from "react";
import type {
  InfiniteData,
  UseInfiniteQueryResult,
} from "@tanstack/react-query";
import { useInfiniteScroll } from "@/hooks";
import type { Feed } from "@/types/feed/feed-read-service";
import type { FeedViewType } from "@/types/feed/common";
import type { Pagination } from "@/types/common";
import { Kitty } from "@/components/common/icons";
import { FeedListOptionGroup } from "../organisms/FeedListOptionGroup";
import { FeedArticle } from "../organisms/FeedArticle";

interface FeedListSectionProps {
  view?: FeedViewType;
  useInfiniteQueryResult: UseInfiniteQueryResult<
    InfiniteData<Pagination<Feed>>
  >;
}

export default function FeedListSection({
  view,
  useInfiniteQueryResult,
}: FeedListSectionProps) {
  const { data, hasNextPage, fetchNextPage, isFetchingNextPage } =
    useInfiniteQueryResult;
  const observerRef = useInfiniteScroll({
    hasNextPage,
    fetchNextPage,
    isFetchingNextPage,
  });

  return (
    <div className="relative">
      <FeedListOptionGroup />

      <section className="flex flex-col pb-1">
        {data
          ? data.pages.map((page) =>
              page.content.map(({ feedUuid }) => (
                <FeedArticle key={feedUuid} {...{ feedUuid, view }} />
              )),
            )
          : null}
        {data?.pages[0].content.length ? null : (
          <div className="h-[20rem] flex gap-2 justify-center items-center font-bold">
            피드가 없어요
            <Kitty />
          </div>
        )}
        <div
          ref={observerRef}
          className="flex gap-4 justify-center items-center text-teal-400"
        >
          {isFetchingNextPage ? (
            <>
              로딩중 <Kitty />
            </>
          ) : null}
        </div>
      </section>
    </div>
  );
}

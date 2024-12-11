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

      <section className="flex flex-col pb-16 md:pb-16 md:pt-0">
        {data
          ? data.pages.map((page) =>
              page.content.map(({ feedUuid }) => (
                <FeedArticle key={feedUuid} {...{ feedUuid, view }} />
              )),
            )
          : null}
        <div ref={observerRef} className="">
          {isFetchingNextPage ? "로딩중" : null}
        </div>
      </section>
    </div>
  );
}

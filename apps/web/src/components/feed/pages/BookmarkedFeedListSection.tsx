"use client";

import React from "react";
import { useInfiniteScroll } from "@/hooks";
import type { FeedViewType } from "@/types/feed/common";
import { useFeedBookmarksInfiniteQuery } from "@/hooks/utility-service/bookmark-service";
import { FeedListOptionGroup } from "../organisms/FeedListOptionGroup";
import { FeedArticle } from "../organisms/FeedArticle";

interface BookmarkedFeedListSectionProps {
  view?: FeedViewType;
}

export function BookmarkedFeedListSection({
  view,
}: BookmarkedFeedListSectionProps) {
  const { data, hasNextPage, fetchNextPage, isFetchingNextPage } =
    useFeedBookmarksInfiniteQuery({});
  const observerRef = useInfiniteScroll({
    hasNextPage,
    fetchNextPage,
    isFetchingNextPage,
  });

  return (
    <div className="relative">
      <FeedListOptionGroup noSort />

      <section className="flex flex-col pb-16 md:pb-16 md:pt-0">
        {data
          ? data.pages.map((page) =>
              page.content.map((feedUuid) => (
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

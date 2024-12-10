"use client";

import React from "react";
import { Separator } from "@repo/ui/shadcn/separator";
import { useGetFeedsInfiniteQuery, useInfiniteScroll } from "@/hooks";
import { FeedCardArticle } from "@/components/feed/organisms/FeedCardArticle";
import type { GetFeedsReq } from "@/types/feed/feed-read-service";
import type { FeedViewType } from "@/types/feed/common";
import { FeedListOptionGroup } from "../organisms/FeedListOptionGroup";
import { FeedCompactArticle } from "../organisms/FeedCompactArticle";

interface FeedListSectionProps
  extends Pick<GetFeedsReq, "categoryName" | "hashtagName" | "sortBy"> {
  view?: FeedViewType;
}

export default function FeedListSection({
  categoryName,
  hashtagName,
  sortBy,
  view,
}: FeedListSectionProps) {
  const { data, hasNextPage, fetchNextPage, isFetchingNextPage } =
    useGetFeedsInfiniteQuery({
      categoryName,
      sortBy,
      hashtagName,
    });
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
              page.content.map((feed) => {
                if (view === "compact")
                  return (
                    <React.Fragment key={feed.feedUuid}>
                      <FeedCompactArticle {...feed} link />
                      <Separator className="bg-[#EEE] h-[0.5rem]" />
                    </React.Fragment>
                  );
                return (
                  <React.Fragment key={feed.feedUuid}>
                    <FeedCardArticle {...feed} link />
                    <Separator className="bg-[#EEE] h-[0.5rem]" />
                  </React.Fragment>
                );
              }),
            )
          : null}
        <div ref={observerRef} className="">
          {isFetchingNextPage ? "로딩중" : null}
        </div>
      </section>
    </div>
  );
}

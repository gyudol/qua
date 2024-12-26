"use client";

import React from "react";
import { useGetFeedsInfiniteQuery } from "@/hooks";
import type { GetFeedsReq } from "@/types/feed/feed-read-service";
import type { FeedViewType } from "@/types/feed/common";
import FeedListSection from "../templates/FeedListSection";

interface FeedListSectionProps
  extends Pick<GetFeedsReq, "categoryName" | "hashtagName" | "sortBy"> {
  view?: FeedViewType;
}

export function FeedPageFeedListSection({
  categoryName,
  hashtagName,
  sortBy,
  view,
}: FeedListSectionProps) {
  const useInfiniteQueryResult = useGetFeedsInfiniteQuery({
    categoryName,
    sortBy,
    hashtagName,
  });

  return <FeedListSection {...{ view, useInfiniteQueryResult }} />;
}

"use client";

import React from "react";
import { useSearchFeedsInfiniteQuery } from "@/hooks";
import type { SearchFeedsReq } from "@/types/feed/feed-read-service";
import type { FeedViewType } from "@/types/feed/common";
import FeedListSection from "../templates/FeedListSection";

interface FeedListSectionProps extends Pick<SearchFeedsReq, "keyword"> {
  view?: FeedViewType;
}

export function SearchPageFeedListSection({
  keyword,
  view,
}: FeedListSectionProps) {
  const useInfiniteQueryResult = useSearchFeedsInfiniteQuery({
    keyword,
  });

  return <FeedListSection {...{ view, useInfiniteQueryResult }} />;
}

"use client";

import React from "react";
import { useGetFeedRecommendationsInfiniteQuery } from "@/hooks";
import type { FeedViewType } from "@/types/feed/common";
import FeedListSection from "../templates/FeedListSection";

interface FeedListSectionProps {
  memberUuid: string;
  view?: FeedViewType;
}

export function FeedRecommendsListSection({
  memberUuid,
  view,
}: FeedListSectionProps) {
  const useInfiniteQueryResult = useGetFeedRecommendationsInfiniteQuery({
    memberUuid,
  });

  return <FeedListSection {...{ view, useInfiniteQueryResult }} />;
}

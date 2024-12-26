"use client";

import { Separator } from "@repo/ui/shadcn/separator";
import { useGetFeedQuery } from "@/hooks";
import type { Feed } from "@/types/feed/feed-read-service";
import { FeedCompactArticle } from "./FeedCompactArticle";
import { FeedCardArticle } from "./FeedCardArticle";

interface FeedArticleProps extends Pick<Feed, "feedUuid"> {
  view?: "compact" | "card";
}

export function FeedArticle({ feedUuid, view }: FeedArticleProps) {
  const { data: feed } = useGetFeedQuery({ feedUuid });
  if (!feed) return;
  return (
    <>
      {view === "compact" ? (
        <FeedCompactArticle {...feed} link />
      ) : (
        <FeedCardArticle {...feed} link />
      )}
      <Separator className="bg-[#EEE] h-[0.5rem]" />
    </>
  );
}

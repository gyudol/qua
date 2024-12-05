"use client";

import { useGetFeedsInfiniteQuery } from "@/hooks";
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
  const { data } = useGetFeedsInfiniteQuery({
    categoryName,
    sortBy,
    hashtagName,
  });

  return (
    <div className="relative">
      <FeedListOptionGroup />
      <section className="flex flex-col pb-16 md:pb-16 md:pt-0">
        {data?.pages.map((page) =>
          page.content.map((feed) => {
            if (view === "compact")
              return <FeedCompactArticle key={feed.feedUuid} {...feed} link />;
            return <FeedCardArticle key={feed.feedUuid} {...feed} link />;
          }),
        )}
      </section>
    </div>
  );
}

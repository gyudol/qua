"use client";

import { useGetFeedsInfiniteQuery } from "@/hooks";
import { FeedCardArticle } from "@/components/feed/organisms/FeedCardArticle";
import FeedSortTap from "../organisms/FeedSortTap";

export default function FeedList() {
  const { data } = useGetFeedsInfiniteQuery({});
  return (
    <div className="relative pt-[5rem]">
      <FeedSortTap />
      <section className="flex flex-col pb-16 md:pb-16 md:pt-0">
        {data?.pages.map((page) =>
          page.content.map((feed) => {
            return <FeedCardArticle key={feed.feedUuid} {...feed} />;
          }),
        )}
      </section>
    </div>
  );
}

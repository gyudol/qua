"use client";

import { useQuery } from "@tanstack/react-query";
import { getAllFeed } from "@/actions/feed";
import Feed from "@/components/feed/organisms/Feed";

export default function FeedList() {
  const { data } = useQuery({
    queryKey: ["feeds"],
    queryFn: async () => getAllFeed(),
  });
  return (
    <section className="flex flex-col gap-[10px] pb-[80px] bg-[#DDD]">
      {data?.content.map((feed) => <Feed key={feed.feedUuid} {...feed} />)}
    </section>
  );
}

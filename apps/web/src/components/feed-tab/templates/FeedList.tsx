"use client";

import { useQuery } from "@tanstack/react-query";
import { cn } from "@repo/ui/lib/utils";
import { Poppins } from "next/font/google";
import { getAllFeed } from "@/actions/feed";
import Feed from "@/components/feed/organisms/Feed";
import { Group, List } from "@/components/common/icons";
import { alertNotImplemented } from "@/functions/utils";

const poppins = Poppins({ weight: "400", subsets: ["latin"] });

export default function FeedList() {
  const { data } = useQuery({
    queryKey: ["feeds"],
    queryFn: async () => getAllFeed(),
  });
  return (
    <>
      <section
        className={cn(
          "w-ful flex justify-between items-center",
          "p-[20px_28px] mb-[10px]",
          "bg-white",
        )}
      >
        <button
          type="button"
          className={cn(poppins.className, "text-[var(--theme-color)]")}
          onClick={() => alertNotImplemented()}
        >
          Sorting by {">"}
        </button>
        <div className="flex items-center gap-[20px]">
          <button type="button" onClick={() => alertNotImplemented()}>
            <List />
          </button>
          <button type="button" onClick={() => alertNotImplemented()}>
            <Group />
          </button>
        </div>
      </section>
      <section className="flex flex-col gap-[10px] pb-[80px]">
        {data?.content.map((feed) => <Feed key={feed.feedUuid} {...feed} />)}
      </section>
    </>
  );
}

"use client";

import { Rows2, Rows4 } from "lucide-react";
import { useRouter, useSearchParams } from "next/navigation";
import { toURLSearchParams } from "@/functions/utils";
import type { GetFeedsReq } from "@/types/feed/feed-read-service";

export function FeedViewTypeToggler() {
  const router = useRouter();
  const searchParams = useSearchParams();

  const sortBy: GetFeedsReq["sortBy"] =
    searchParams.get("sortBy") === "likes" ? "likes" : "latest";

  const viewType = searchParams.get("viewType");
  const views = [
    { value: "card", Icon: Rows2 },
    { value: "compact", Icon: Rows4 },
  ];

  function handleClick(value: string) {
    router.push(`?${toURLSearchParams({ viewType: value, sortBy })}`);
  }

  return (
    <ul className="flex gap-[1rem] items-center">
      {views.map(({ value, Icon }) => (
        <li key={value} className="flex items-center">
          <button type="button" onClick={() => handleClick(value)}>
            <Icon color={viewType === value ? "#48d0bf" : "lightgray"} />
          </button>
        </li>
      ))}
    </ul>
  );
}

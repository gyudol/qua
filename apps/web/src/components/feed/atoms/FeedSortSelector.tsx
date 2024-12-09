"use client";

import * as React from "react";
import {
  Select,
  SelectContent,
  SelectGroup,
  SelectItem,
  SelectLabel,
  SelectTrigger,
  SelectValue,
} from "@repo/ui/shadcn/select";
import { useRouter, useSearchParams } from "next/navigation";
import type { GetFeedsReq } from "@/types/feed/feed-read-service";
import { toURLSearchParams } from "@/functions/utils";

export function FeedSortSelector() {
  const router = useRouter();
  const searchParams = Object.fromEntries(useSearchParams().entries());
  const sortBy: GetFeedsReq["sortBy"] =
    searchParams.sortBy === "likes" ? "likes" : "latest";

  function handleChange(value: string) {
    router.push(`?${toURLSearchParams({ ...searchParams, sortBy: value })}`);
  }

  return (
    <Select onValueChange={handleChange}>
      <SelectTrigger
        className="
          w-[8rem]
        text-teal-400 text-sm font-medium 
          border-[0] focus:border-0 focus:ring-opacity-0 focus:ring-white
        "
      >
        <SelectValue placeholder="Sort by" defaultValue={sortBy} />
      </SelectTrigger>
      <SelectContent className="border-none outline-none text-teal-400">
        <SelectGroup>
          <SelectLabel>Sort by</SelectLabel>
          <SelectItem value="latest">New</SelectItem>
          <SelectItem value="likes">Like</SelectItem>
        </SelectGroup>
      </SelectContent>
    </Select>
  );
}

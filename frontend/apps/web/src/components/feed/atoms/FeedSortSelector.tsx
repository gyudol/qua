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
import { toURLSearchParams } from "@/functions/utils";
import { useSessionContext } from "@/context/SessionContext";

export function FeedSortSelector() {
  const { memberUuid } = useSessionContext();
  const router = useRouter();
  const searchParams = Object.fromEntries(useSearchParams().entries());

  let sortBy: "latest" | "likes" | "recommends";
  const _sortBy = searchParams.sortBy;

  if (_sortBy === "likes") sortBy = "likes";
  else if (_sortBy === "latest") sortBy = "latest";
  else if (memberUuid) sortBy = "recommends";
  else sortBy = "latest";

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
          {memberUuid ? (
            <SelectItem value="recommends">Recommend</SelectItem>
          ) : null}
          <SelectItem value="latest">Latest</SelectItem>
          <SelectItem value="likes">Like</SelectItem>
        </SelectGroup>
      </SelectContent>
    </Select>
  );
}

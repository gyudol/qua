"use client";

import Link from "next/link";
import { useSearchParams } from "next/navigation";
import type { Hashtag } from "@/types/contents";
import { toURLSearchParams } from "@/functions/utils";

type FeedHashtagProps = Hashtag;

export function FeedHashtag({ name }: FeedHashtagProps) {
  const searchParams = Object.fromEntries(useSearchParams().entries());

  return (
    <Link
      href={`/search?${toURLSearchParams({ ...searchParams, tag: name })}`}
      className="text-teal-500 text-xs font-bold bg-slate-50 rounded-xl py-1 px-2"
      aria-label={`해시태그 검색: #${name}`}
    >
      #{name}
    </Link>
  );
}

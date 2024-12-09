import Link from "next/link";
import type { Hashtag } from "@/types/contents";

type FeedHashtagProps = Hashtag;

export function FeedHashtag({ name }: FeedHashtagProps) {
  return (
    <Link
      href={`/search?tag=${name}`}
      className="text-teal-500 text-xs font-bold bg-slate-50 rounded-xl py-1 px-2"
      aria-label={`해시태그 검색: #${name}`}
    >
      #{name}
    </Link>
  );
}

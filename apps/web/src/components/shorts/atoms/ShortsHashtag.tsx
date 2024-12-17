import Link from "next/link";
import type { Hashtag } from "@/types/contents";

type ShortsHashtagProps = Hashtag;

export function ShortsHashtag({ name }: ShortsHashtagProps) {
  return (
    <Link
      href={`/search?keyword=%23${name}`}
      className="text-white text-xs font-bold bg-[rgba(0,0,0,0.20)] rounded-xl py-1 px-2"
      aria-label={`해시태그 검색: #${name}`}
    >
      #{name}
    </Link>
  );
}

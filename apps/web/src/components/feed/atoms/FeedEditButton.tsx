import { Pencil } from "lucide-react";
import Link from "next/link";
import type { Feed } from "@/types/contents";

type FeedEditButtonProps = Pick<Feed, "feedUuid">;

export function FeedEditButton({ feedUuid }: FeedEditButtonProps) {
  return (
    <Link
      href={`/feed-eidt/${feedUuid}`}
      className="w-full px-4 py-2 text-left hover:bg-gray-50 flex items-center gap-2"
    >
      <Pencil className="w-4 h-4" />
      수정하기
    </Link>
  );
}

"use client";

import { MessageSquareMore } from "lucide-react";
import Link from "next/link";
import type { Feed } from "@/types/feed/feed-read-service";

type FeedCommentButtonProps = Pick<Feed, "feedUuid" | "commentCount">;

export function FeedCommentButton({
  feedUuid,
  commentCount,
}: FeedCommentButtonProps) {
  return (
    <Link
      href={`/feeds/${feedUuid}#comment`}
      className="flex gap-[0.5rem] items-center"
    >
      <span>
        <MessageSquareMore size="1.25rem" color="#B1B1B1" />
      </span>
      <span className="text-sm text-gray-500">{commentCount}</span>
    </Link>
  );
}

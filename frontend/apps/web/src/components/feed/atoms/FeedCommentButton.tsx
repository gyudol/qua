"use client";

import { MessageSquareMore } from "lucide-react";
import Link from "next/link";
import type { Feed } from "@/types/feed/feed-read-service";
import { useFeedCommentDrawerContext } from "@/context/FeedCommentDrawerContext";

type FeedCommentButtonProps = Pick<Feed, "feedUuid" | "commentCount">;

export function FeedCommentButton({
  feedUuid,
  commentCount,
}: FeedCommentButtonProps) {
  const { setOpen } = useFeedCommentDrawerContext();
  if (setOpen)
    return (
      <button
        type="button"
        className="flex gap-[0.5rem] items-center text-slate-400"
        onClick={() => setOpen((prev) => !prev)}
      >
        <span>
          <MessageSquareMore size="1.25rem" />
        </span>
        <span className="text-xs">{commentCount}</span>
      </button>
    );
  return (
    <Link
      href={`/feeds/${feedUuid}#comment`}
      className="flex gap-[0.5rem] items-center text-slate-400"
    >
      <span>
        <MessageSquareMore size="1.25rem" />
      </span>
      <span className="text-xs">{commentCount}</span>
    </Link>
  );
}

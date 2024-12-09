"use client";

import { MessageSquareMore } from "lucide-react";
import type { Shorts } from "@/types/shorts/shorts-read-service";
import { useCommentDrawerContext } from "@/context/DrawerContext";

type ShortsCommentButtonProps = Pick<Shorts, "commentCount">;

export function ShortsCommentButton({
  commentCount,
}: ShortsCommentButtonProps) {
  const { setOpen } = useCommentDrawerContext();

  function handleClick() {
    if (setOpen) setOpen((open) => !open);
  }
  return (
    <button
      type="button"
      className="flex gap-[0.5rem] items-center text-slate-400"
      onClick={handleClick}
    >
      <span>
        <MessageSquareMore size="1.25rem" />
      </span>
      <span className="text-sm">{commentCount}</span>
    </button>
  );
}

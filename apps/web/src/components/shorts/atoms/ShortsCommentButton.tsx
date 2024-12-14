"use client";

import { MessageSquareMore } from "lucide-react";
import { cn } from "@repo/ui/lib/utils";
import type { Shorts } from "@/types/shorts/shorts-read-service";
import { useCommentDrawerContext } from "@/context/DrawerContext";
import { formatToNumAbbrs } from "@/functions/utils";

type ShortsCommentButtonProps = Pick<Shorts, "commentCount">;

export function ShortsCommentButton({
  commentCount,
}: ShortsCommentButtonProps) {
  const { setOpen } = useCommentDrawerContext();

  function handleClick() {
    if (setOpen) setOpen((open) => !open);
  }
  return (
    <div className="flex flex-col items-center">
      <button
        type="button"
        className={cn(
          "flex justify-center items-center",
          "size-[3rem] rounded-full",
          "bg-[rgba(0,0,0,0.20)]",
        )}
        onClick={handleClick}
      >
        <MessageSquareMore
          size="1.5rem"
          stroke="none"
          className="stroke-white"
        />
      </button>

      <span className="text-sm text-white">
        {formatToNumAbbrs(commentCount)}
      </span>
    </div>
  );
}

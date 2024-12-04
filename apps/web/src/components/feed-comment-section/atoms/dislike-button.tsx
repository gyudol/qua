"use client";

import { ThumbsDown } from "lucide-react";
import { useSession } from "next-auth/react";
import type { UseMutationResult } from "@tanstack/react-query";

interface DislikeButtonProps {
  dislikeCount: number;
  dislikeStatus: {
    data: boolean | undefined;
    mutation: UseMutationResult<
      void,
      Error,
      void,
      {
        prevLikeStatus: boolean | undefined;
        prevDislikeStatus: boolean | undefined;
      }
    >;
  };
}

export function DislikeButton({
  dislikeCount,
  dislikeStatus,
}: DislikeButtonProps) {
  const { status } = useSession();

  function toggleLike() {
    if (status === "authenticated") {
      dislikeStatus.mutation.mutate();
    }
  }

  return (
    <button type="button" onClick={toggleLike} className="flex">
      <span>
        <ThumbsDown stroke={dislikeStatus.data ? "pink" : "black"} />
      </span>
      <span>{dislikeCount + Number(dislikeStatus.data)}</span>
    </button>
  );
}

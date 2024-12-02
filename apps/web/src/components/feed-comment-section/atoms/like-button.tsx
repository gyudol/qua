"use client";

import { ThumbsUp } from "lucide-react";
import { useSession } from "next-auth/react";
import type { UseMutationResult } from "@tanstack/react-query";

interface LikeButtonProps {
  likeCount: number;
  likeStatus: {
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

export function LikeButton({ likeCount, likeStatus }: LikeButtonProps) {
  const { status } = useSession();

  function toggleLike() {
    if (status === "authenticated") {
      likeStatus.mutation.mutate();
    }
  }

  return (
    <button type="button" onClick={toggleLike} className="flex">
      <span>
        <ThumbsUp stroke={likeStatus.data ? "skyblue" : "black"} />
      </span>
      <span>{likeCount + Number(likeStatus.data)}</span>
    </button>
  );
}

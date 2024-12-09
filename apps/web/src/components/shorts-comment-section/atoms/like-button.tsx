import type { UseMutationResult } from "@tanstack/react-query";
import { ThumbsUp } from "lucide-react";
import { ButtonWithAuth } from "@/components/common/atoms";
import { formatToNumAbbrs } from "@/functions/utils";

interface ShortsLikeButtonProps {
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

export function LikeButton({ likeCount, likeStatus }: ShortsLikeButtonProps) {
  return (
    <ButtonWithAuth
      className="flex gap-[0.5rem] items-center"
      onClick={() => likeStatus.mutation.mutate()}
    >
      <span>
        <ThumbsUp
          size="1.25rem"
          className={likeStatus.data ? "text-sky-300" : "text-slate-400"}
        />
      </span>
      <span className="text-sm text-slate-400">
        {formatToNumAbbrs(Number(likeCount) + Number(likeStatus.data || 0))}
      </span>
    </ButtonWithAuth>
  );
}

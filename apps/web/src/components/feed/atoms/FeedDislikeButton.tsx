import type { UseMutationResult } from "@tanstack/react-query";
import { ThumbsDown } from "lucide-react";
import { ButtonWithAuth } from "@/components/common/atoms";

interface FeedDislikeButtonProps {
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

export function FeedDislikeButton({
  // dislikeCount,
  dislikeStatus,
}: FeedDislikeButtonProps) {
  return (
    <ButtonWithAuth
      className="flex gap-[0.5rem] items-center"
      onClick={() => dislikeStatus.mutation.mutate()}
    >
      <span>
        <ThumbsDown
          size="1.25rem"
          className={dislikeStatus.data ? "text-pink-300" : "text-slate-400"}
        />
      </span>
      {/* <span className="text-xs text-slate-400">
        {Number(dislikeCount) + Number(dislikeStatus.data || 0)}
      </span> */}
    </ButtonWithAuth>
  );
}

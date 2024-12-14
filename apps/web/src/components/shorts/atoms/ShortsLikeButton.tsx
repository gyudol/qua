import type { UseMutationResult } from "@tanstack/react-query";
import { ThumbsUp } from "lucide-react";
import { cn } from "@repo/ui/lib/utils";
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

export function ShortsLikeButton({
  likeCount,
  likeStatus,
}: ShortsLikeButtonProps) {
  return (
    <div className="flex flex-col items-center">
      <ButtonWithAuth
        className={cn(
          "flex justify-center items-center",
          "size-[3rem] rounded-full",
          "bg-[rgba(0,0,0,0.20)]",
        )}
        onClick={() => likeStatus.mutation.mutate()}
      >
        <ThumbsUp
          size="1.5rem"
          stroke="none"
          className={likeStatus.data ? "fill-white" : "stroke-white"}
        />
      </ButtonWithAuth>

      <span className="text-sm text-white">
        {formatToNumAbbrs(Number(likeCount) + Number(likeStatus.data || 0))}
      </span>
    </div>
  );
}

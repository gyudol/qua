import type { UseMutationResult } from "@tanstack/react-query";
import { ThumbsDown } from "lucide-react";
import { cn } from "@repo/ui/lib/utils";
import { ButtonWithAuth } from "@/components/common/atoms";
// import { formatToNumAbbrs } from "@/functions/utils";

interface ShortsDislikeButtonProps {
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

export function ShortsDislikeButton({
  // dislikeCount,
  dislikeStatus,
}: ShortsDislikeButtonProps) {
  return (
    <div className="flex flex-col items-center">
      <ButtonWithAuth
        className={cn(
          "flex justify-center items-center",
          "size-[3rem] rounded-full",
          "bg-[rgba(0,0,0,0.20)]",
        )}
        onClick={() => dislikeStatus.mutation.mutate()}
      >
        <ThumbsDown
          size="1.5rem"
          stroke="none"
          className={dislikeStatus.data ? "fill-white" : "stroke-white"}
        />
      </ButtonWithAuth>

      <span className="text-sm text-white">
        싫어요
        {/* {formatToNumAbbrs(
          Number(dislikeCount) + Number(dislikeStatus.data || 0),
        )} */}
      </span>
    </div>
  );
}

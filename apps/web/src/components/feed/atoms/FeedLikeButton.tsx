import type { UseMutationResult } from "@tanstack/react-query";
import { ThumbsUp } from "lucide-react";
import { ButtonWithAuth } from "@/components/common/atoms";

interface FeedLikeButtonProps {
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

export function FeedLikeButton({ likeCount, likeStatus }: FeedLikeButtonProps) {
  return (
    <ButtonWithAuth
      className="flex gap-[0.5rem] items-center"
      onClick={() => likeStatus.mutation.mutate()}
    >
      <span>
        <ThumbsUp
          size="1.25rem"
          color={likeStatus.data ? "skyblue" : "#B1B1B1"}
        />
      </span>
      <span className="text-sm text-gray-500">
        {Number(likeCount) + Number(likeStatus.data || 0)}
      </span>
    </ButtonWithAuth>
  );
}

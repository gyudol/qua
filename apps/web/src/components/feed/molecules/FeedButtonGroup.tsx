"use client";

import { useLikeService } from "@/hooks";
import type { Feed } from "@/types/feed/feed-read-service";
import { FeedLikeButton } from "../atoms/FeedLikeButton";
import { FeedDislikeButton } from "../atoms/FeedDislikeButton";
import { FeedCommentButton } from "../atoms/FeedCommentButton";
import { FeedSendButton } from "../atoms/FeedSendButton";
import { FeedBookmarkButton } from "../atoms/FeedBookmarkButton";

type FeedButtonGroupProps = Pick<
  Feed,
  "feedUuid" | "likeCount" | "dislikeCount" | "commentCount"
>;

export function FeedButtonGroup({
  feedUuid,
  likeCount,
  dislikeCount,
  commentCount,
}: FeedButtonGroupProps) {
  const { likeStatus, dislikeStatus } = useLikeService({
    kind: "feed",
    kindUuid: feedUuid,
  });

  return (
    <div className="flex justify-between items-center py-[0.5rem]">
      <div className="flex gap-[0.5rem]">
        <FeedLikeButton {...{ likeCount, likeStatus }} />
        <FeedDislikeButton {...{ dislikeCount, dislikeStatus }} />
        <FeedCommentButton {...{ feedUuid, commentCount }} />
        <FeedSendButton {...{ feedUuid }} />
      </div>
      <div>
        <FeedBookmarkButton {...{ feedUuid }} />
      </div>
    </div>
  );
}

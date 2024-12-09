"use client";

import type { Shorts } from "@/types/shorts/shorts-read-service";
import { useLikeService } from "@/hooks";
import {
  ShortsBookmarkButton,
  ShortsCommentButton,
  ShortsDislikeButton,
  ShortsLikeButton,
  // ShortsMoreButton,
  ShortsSendButton,
} from "../atoms";

type ShortsButtonGroupProps = Pick<
  Shorts,
  "shortsUuid" | "likeCount" | "dislikeCount" | "commentCount"
>;

export function ShortsButtonGroup({
  shortsUuid,
  likeCount,
  dislikeCount,
  commentCount,
}: ShortsButtonGroupProps) {
  const { likeStatus, dislikeStatus } = useLikeService({
    kind: "shorts",
    kindUuid: shortsUuid,
    likeCount,
    dislikeCount,
  });
  return (
    <div className="absolute bottom-12 right-3 flex flex-col gap-y-4 text-white">
      <ShortsLikeButton {...{ likeStatus, likeCount }} />
      <ShortsDislikeButton {...{ dislikeStatus, dislikeCount }} />
      <ShortsCommentButton {...{ commentCount }} />
      <ShortsBookmarkButton {...{ shortsUuid }} />
      <ShortsSendButton {...{ shortsUuid }} />
      {/* <ShortsMoreButton /> */}
    </div>
  );
}

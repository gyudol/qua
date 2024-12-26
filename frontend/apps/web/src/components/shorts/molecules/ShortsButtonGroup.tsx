"use client";

import type { Shorts } from "@/types/shorts/shorts-read-service";
import { useLikeService } from "@/hooks";
import {
  ShortsBookmarkButton,
  ShortsCommentButton,
  ShortsDislikeButton,
  ShortsLikeButton,
  ShortsSendButton,
} from "../atoms";
import { ShortsMoreOption } from "./ShortsMoreOption";

type ShortsButtonGroupProps = Pick<
  Shorts,
  "shortsUuid" | "likeCount" | "dislikeCount" | "commentCount" | "memberUuid"
>;

export function ShortsButtonGroup({
  shortsUuid,
  likeCount,
  dislikeCount,
  commentCount,
  memberUuid,
}: ShortsButtonGroupProps) {
  const { likeStatus, dislikeStatus } = useLikeService({
    kind: "shorts",
    kindUuid: shortsUuid,
    likeCount,
    dislikeCount,
  });

  return (
    <div className="absolute bottom-12 right-3 z-10 flex flex-col gap-y-4 text-white">
      <ShortsLikeButton {...{ likeStatus, likeCount }} />
      <ShortsDislikeButton {...{ dislikeStatus, dislikeCount }} />
      <ShortsCommentButton {...{ commentCount }} />
      <ShortsBookmarkButton {...{ shortsUuid }} />
      <ShortsSendButton {...{ shortsUuid }} />
      <ShortsMoreOption {...{ shortsUuid, memberUuid: memberUuid || "" }} />
    </div>
  );
}

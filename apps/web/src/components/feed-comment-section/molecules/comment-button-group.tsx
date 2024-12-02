"use client";

import { useState } from "react";
import { useLikeService } from "@/hooks";
import { DislikeButton, LikeButton, ReplyButton, ReplyInput } from "../atoms";

interface CommentButtonGroupProps {
  commentUuid: string;
  likeCount: number;
  dislikeCount: number;
}

export function CommentButtonGroup({
  commentUuid,
  likeCount,
  dislikeCount,
}: CommentButtonGroupProps) {
  const [isReplyInputShowed, setIsReplyInputShowed] = useState<boolean>(false);
  const { likeStatus, dislikeStatus } = useLikeService({
    kind: "feed-comment",
    kindUuid: commentUuid,
  });

  return (
    <>
      <ul className="flex">
        <li>
          <LikeButton {...{ likeCount, likeStatus }} />
        </li>
        <li>
          <DislikeButton
            {...{
              dislikeCount,
              dislikeStatus,
            }}
          />
        </li>
        <li>
          <ReplyButton {...{ setIsReplyInputShowed }} />
        </li>
      </ul>
      {isReplyInputShowed ? (
        <div>
          <ReplyInput {...{ commentUuid, setIsReplyInputShowed }} />
        </div>
      ) : null}
    </>
  );
}

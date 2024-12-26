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
    kind: "shorts-comment",
    kindUuid: commentUuid,
    likeCount,
    dislikeCount,
  });

  return (
    <>
      <ul className="flex gap-[1.5rem] items-center">
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
        <div className="my-[0.5rem]">
          <ReplyInput {...{ commentUuid, setIsReplyInputShowed }} />
        </div>
      ) : null}
    </>
  );
}

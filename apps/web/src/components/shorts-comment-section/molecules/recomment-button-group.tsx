"use client";

import { useState } from "react";
import { useLikeService } from "@/hooks";
import { DislikeButton, LikeButton, ReplyButton, ReplyInput } from "../atoms";

interface RecommentButtonGroupProps {
  commentUuid: string;
  recommentUuid: string;
  likeCount: number;
  dislikeCount: number;
}

export function RecommentButtonGroup({
  commentUuid,
  recommentUuid,
  likeCount,
  dislikeCount,
}: RecommentButtonGroupProps) {
  const [isReplyInputShowed, setIsReplyInputShowed] = useState<boolean>(false);
  const { likeStatus, dislikeStatus } = useLikeService({
    kind: "shorts-recomment",
    kindUuid: recommentUuid,
    likeCount,
    dislikeCount,
  });

  return (
    <>
      <ul className="flex gap-[0.5rem] items-center">
        <li>
          <LikeButton
            {...{
              likeCount,
              likeStatus,
            }}
          />
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

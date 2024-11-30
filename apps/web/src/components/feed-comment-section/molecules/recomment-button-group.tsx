"use client";

import type { Dispatch, SetStateAction } from "react";
import { useState } from "react";
import type { FeedRecomment } from "@/types/comment/comment-read-service";
import { useLikeService } from "@/hooks";
import { DislikeButton, LikeButton, ReplyButton, ReplyInput } from "../atoms";

interface RecommentButtonGroupProps {
  commentUuid: string;
  recommentUuid: string;
  likeCount: number;
  dislikeCount: number;
  setRecommentList: Dispatch<SetStateAction<FeedRecomment[]>>;
}

export function RecommentButtonGroup({
  commentUuid,
  recommentUuid,
  likeCount,
  dislikeCount,
  setRecommentList,
}: RecommentButtonGroupProps) {
  const [isReplyInputShowed, setIsReplyInputShowed] = useState<boolean>(false);
  const { likeStatus, dislikeStatus } = useLikeService({
    kind: "feed-recomment",
    kindUuid: recommentUuid,
  });

  return (
    <>
      <ul className="flex">
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
        <div>
          <ReplyInput
            {...{ commentUuid, setRecommentList, setIsReplyInputShowed }}
          />
        </div>
      ) : null}
    </>
  );
}

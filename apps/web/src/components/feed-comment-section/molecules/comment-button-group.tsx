"use client";

import type { Dispatch, SetStateAction } from "react";
import { useState } from "react";
import type { FeedRecomment } from "@/types/comment/comment-read-service";
import { useLikeService } from "@/hooks";
import { DislikeButton, LikeButton, ReplyButton, ReplyInput } from "../atoms";

interface CommentButtonGroupProps {
  commentUuid: string;
  likeCount: number;
  dislikeCount: number;
  setRecommentList: Dispatch<SetStateAction<FeedRecomment[]>>;
}

export function CommentButtonGroup({
  commentUuid,
  likeCount,
  dislikeCount,
  setRecommentList,
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
          <ReplyInput
            {...{ commentUuid, setRecommentList, setIsReplyInputShowed }}
          />
        </div>
      ) : null}
    </>
  );
}

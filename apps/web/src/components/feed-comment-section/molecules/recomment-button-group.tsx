"use client";

import type { Dispatch, SetStateAction } from "react";
import { useState } from "react";
import type { FeedRecomment } from "@/types/comment/comment-read-service";
import {
  RecommentDislikeButton,
  RecommentLikeButton,
  ReplyButton,
  ReplyInput,
} from "../atoms";

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
  const [isLikeOrDislike, setIsLikeOrDislike] = useState<
    "like" | "none" | "dislike"
  >("none");

  return (
    <>
      <ul className="flex">
        <li>
          <RecommentLikeButton
            {...{
              recommentUuid,
              likeCount,
              isLikeOrDislike,
              setIsLikeOrDislike,
            }}
          />
        </li>
        <li>
          <RecommentDislikeButton
            {...{
              recommentUuid,
              dislikeCount,
              isLikeOrDislike,
              setIsLikeOrDislike,
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

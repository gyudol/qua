"use client";

import type { Dispatch, SetStateAction } from "react";
import { useState } from "react";
import type { FeedRecomment } from "@/types/comment/comment-read-service";
import {
  CommentDislikeButton,
  CommentLikeButton,
  ReplyButton,
  ReplyInput,
} from "../atoms";

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

  const [isLikeOrDislike, setIsLikeOrDislike] = useState<
    "like" | "none" | "dislike"
  >("none");

  return (
    <>
      <ul className="flex">
        <li>
          <CommentLikeButton
            {...{ commentUuid, likeCount, isLikeOrDislike, setIsLikeOrDislike }}
          />
        </li>
        <li>
          <CommentDislikeButton
            {...{
              commentUuid,
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

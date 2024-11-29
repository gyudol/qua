"use client";

import { useSession } from "next-auth/react";
import type { ChangeEvent, Dispatch, SetStateAction } from "react";
import { useState } from "react";
import { postFeedRecomment } from "@/actions/comment-service";
import { alertNotImplemented } from "@/functions/utils";
import type { FeedRecomment } from "@/types/comment/comment-read-service";

interface ReplyInputProps {
  commentUuid: string;
  setRecommentList: Dispatch<SetStateAction<FeedRecomment[]>>;
  setIsReplyInputShowed: Dispatch<SetStateAction<boolean>>;
}

export function ReplyInput({
  commentUuid,
  setRecommentList,
  setIsReplyInputShowed,
}: ReplyInputProps) {
  const { status, data } = useSession();

  const lengthLimit = 300;
  const [content, setContent] = useState("");
  const [isFocused, setIsFocused] = useState<boolean>(false);

  function handleChange(e: ChangeEvent<HTMLTextAreaElement>) {
    setContent(e.target.value.slice(0, lengthLimit));
  }

  function handleClose() {
    setIsReplyInputShowed(false);
  }

  function handlePost() {
    if (status === "authenticated") {
      setContent("");
      setIsReplyInputShowed(false);
      const session = data as { user: { memberUuid: string } };
      void postFeedRecomment({
        commentUuid,
        content,
        memberUuid: session.user.memberUuid,
      }).then((recomment) =>
        setRecommentList((prevRecommentList) => [
          { ...recomment, likeCount: 0, dislikeCount: 0 },
          ...prevRecommentList,
        ]),
      );
    } else if (status === "unauthenticated") {
      alertNotImplemented({ message: "로그인 하세요" });
    }
  }

  return (
    <div className={isFocused ? "ring-2 ring-blue-500" : ""}>
      <div className="h-[3rem]">
        <textarea
          className="w-full h-full resize-none focus:outline-none"
          placeholder="답글 추가..."
          value={content}
          onChange={handleChange}
          onFocus={() => setIsFocused(true)}
          onBlur={() => setIsFocused(false)}
        />
      </div>
      <div className="flex justify-between items-center h-[2rem] bg-white">
        <div>
          <span className="text-sm text-gray-500">
            {content.length}/{lengthLimit}
          </span>
        </div>
        <div>
          <button
            className="px-4 py-1 text-sm text-gray-500 bg-gray-200 rounded-full"
            type="button"
            onClick={() => handleClose()}
          >
            취소
          </button>
          <button
            className="px-4 py-1 text-sm text-white bg-blue-500 rounded-full"
            type="button"
            onClick={() => handlePost()}
            disabled={status === "loading" || !content}
          >
            작성
          </button>
        </div>
      </div>
    </div>
  );
}

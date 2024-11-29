"use client";

import { useSession } from "next-auth/react";
import type { ChangeEvent, Dispatch, SetStateAction } from "react";
import { useState } from "react";
import { postFeedComment } from "@/actions/comment-service";
import { alertNotImplemented } from "@/functions/utils";
import type { FeedComment } from "@/types/comment/comment-read-service";

interface CommentInputProps {
  feedUuid: string;
  setCommentList: Dispatch<SetStateAction<FeedComment[]>>;
}

export function CommentInput({ feedUuid, setCommentList }: CommentInputProps) {
  const { status, data } = useSession();

  const lengthLimit = 300;
  const [content, setContent] = useState<string>("");
  const [isFocused, setIsFocused] = useState<boolean>(false);

  function handleChange(e: ChangeEvent<HTMLTextAreaElement>) {
    setContent(e.target.value.slice(0, lengthLimit));
  }

  function handlePost() {
    if (status === "authenticated") {
      setContent("");
      setIsFocused(false);
      const session = data as { user: { memberUuid: string } };
      void postFeedComment({
        feedUuid,
        content,
        memberUuid: session.user.memberUuid,
      }).then((comment) =>
        setCommentList((prevCommentList) => [
          { ...comment, likeCount: 0, dislikeCount: 0, recommentCount: 0 },
          ...prevCommentList,
        ]),
      );
    } else if (status === "unauthenticated") {
      alertNotImplemented({ message: "로그인 하세요" });
    }
  }

  function handleCancle() {
    setContent("");
    setIsFocused(false);
  }

  return (
    <div className={isFocused ? "ring-2 ring-blue-500" : ""}>
      <div
        className={`transition-all duration-200 ${isFocused ? "h-[8rem]" : "h-[3rem]"}`}
      >
        <textarea
          className="w-full h-full resize-none focus:outline-none"
          placeholder="댓글 추가..."
          value={content}
          onChange={handleChange}
          onFocus={() => setIsFocused(true)}
        />
      </div>
      {isFocused ? (
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
              onClick={() => handleCancle()}
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
      ) : null}
    </div>
  );
}

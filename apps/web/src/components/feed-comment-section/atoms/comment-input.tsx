"use client";

import { useSession } from "next-auth/react";
import type { ChangeEvent } from "react";
import { useState } from "react";
import { alertNotImplemented } from "@/functions/utils";
import { usePostFeedCommentMutation } from "@/hooks/comment-service";

interface CommentInputProps {
  feedUuid: string;
}

export function CommentInput({ feedUuid }: CommentInputProps) {
  const { status, data } = useSession();
  const mutation = usePostFeedCommentMutation({ feedUuid });

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
      mutation.mutate({ content, memberUuid: session.user.memberUuid });
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

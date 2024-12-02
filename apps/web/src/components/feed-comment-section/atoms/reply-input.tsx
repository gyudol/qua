"use client";

import { useSession } from "next-auth/react";
import type { ChangeEvent, Dispatch, SetStateAction } from "react";
import { useState } from "react";
import { alertNotImplemented } from "@/functions/utils";
import { usePostFeedRecommentMutation } from "@/hooks";

interface ReplyInputProps {
  commentUuid: string;
  setIsReplyInputShowed: Dispatch<SetStateAction<boolean>>;
}

export function ReplyInput({
  commentUuid,
  setIsReplyInputShowed,
}: ReplyInputProps) {
  const { status, data } = useSession();

  const lengthLimit = 300;
  const [content, setContent] = useState("");
  const [isFocused, setIsFocused] = useState<boolean>(false);
  const mutation = usePostFeedRecommentMutation({ commentUuid });

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
      mutation.mutate({ content, memberUuid: session.user.memberUuid });
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

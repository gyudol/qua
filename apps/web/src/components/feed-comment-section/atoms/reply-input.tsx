"use client";

import type { ChangeEvent, Dispatch, SetStateAction } from "react";
import { useState } from "react";
import { usePostFeedRecommentMutation } from "@/hooks";
import { useSessionContext } from "@/context/SessionContext";
import { ButtonWithAuth } from "@/components/common/atoms";

interface ReplyInputProps {
  commentUuid: string;
  setIsReplyInputShowed: Dispatch<SetStateAction<boolean>>;
}

export function ReplyInput({
  commentUuid,
  setIsReplyInputShowed,
}: ReplyInputProps) {
  const { memberUuid } = useSessionContext();

  const lengthLimit = 1000;
  const [content, setContent] = useState("");
  const [isFocused, setIsFocused] = useState<boolean>(false);
  const { mutate } = usePostFeedRecommentMutation({ commentUuid });

  function handleChange(e: ChangeEvent<HTMLTextAreaElement>) {
    setContent(e.target.value.slice(0, lengthLimit));
  }

  function handleClose() {
    setIsReplyInputShowed(false);
  }

  function handlePost() {
    if (!memberUuid) return;
    mutate({ content, memberUuid });
    setContent("");
    setIsReplyInputShowed(false);
  }

  return (
    <div
      className={`w-full p-[0.25rem] bg-white ring-2 ${isFocused ? " ring-teal-400" : "ring-teal-100"}`}
    >
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
      <div className="flex justify-between items-center h-[2rem]">
        <div>
          <span className="text-sm text-slate-400">
            {content.length}/{lengthLimit}
          </span>
        </div>
        <div>
          <button
            className="px-4 py-1 text-sm text-slate-400 bg-slate-200 rounded-full"
            type="button"
            onClick={() => handleClose()}
          >
            취소
          </button>
          <ButtonWithAuth
            className="px-4 py-1 text-sm text-white bg-teal-400 rounded-full"
            onClick={() => handlePost()}
            disabled={status === "loading" || !content}
          >
            작성
          </ButtonWithAuth>
        </div>
      </div>
    </div>
  );
}

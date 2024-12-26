"use client";

import type { ChangeEvent, Dispatch, SetStateAction } from "react";
import { useState } from "react";
import { usePostShortsRecommentMutation } from "@/hooks";
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
  const { mutate } = usePostShortsRecommentMutation({ commentUuid });

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
      className={`w-full p-[0.25rem] !rounded-lg border-[1px] ${isFocused ? " border-teal-400" : "border-teal-100"}`}
    >
      <div className="h-[3rem]">
        <textarea
          className="w-full h-full resize-none focus:outline-none placeholder:text-sm text-sm p-2"
          placeholder="답글 추가..."
          value={content}
          onChange={handleChange}
          onFocus={() => setIsFocused(true)}
          onBlur={() => setIsFocused(false)}
          spellCheck={false}
        />
      </div>
      <div className="flex justify-between items-center">
        <p className="p-2 text-xs text-slate-400">
          {content.length} / {lengthLimit}
        </p>
        <div className="flex gap-[0.25rem] p-2">
          <button
            className="px-4 py-1 text-sm text-slate-400 bg-slate-200 !rounded-md"
            type="button"
            onClick={() => handleClose()}
          >
            취소
          </button>
          <ButtonWithAuth
            className="px-4 py-1 text-sm text-white bg-teal-400 !rounded-md"
            type="button"
            onClick={() => handlePost()}
          >
            작성
          </ButtonWithAuth>
        </div>
      </div>
    </div>
  );
}

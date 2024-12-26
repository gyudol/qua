"use client";

import type { ChangeEvent, Dispatch, SetStateAction } from "react";
import { useState } from "react";
import { usePutShortsRecommentMutation } from "@/hooks";
import { ButtonWithAuth } from "@/components/common/atoms";

interface RecommentEditInputProps {
  recommentUuid: string;
  content: string;
  setIsEditing: Dispatch<SetStateAction<boolean>>;
}

export function RecommentEditInput({
  recommentUuid,
  content,
  setIsEditing,
}: RecommentEditInputProps) {
  const mutation = usePutShortsRecommentMutation({ recommentUuid });

  const lengthLimit = 300;
  const [newContent, setNewContent] = useState(content);
  const [isFocused, setIsFocused] = useState<boolean>(false);

  function handleChange(e: ChangeEvent<HTMLTextAreaElement>) {
    setNewContent(e.target.value.slice(0, lengthLimit));
  }

  function handleClose() {
    setIsEditing(false);
  }

  function handleEdit() {
    setNewContent("");
    setIsEditing(false);
    mutation.mutate(newContent);
  }

  return (
    <div
      className={`w-full p-2 bg-white border-[1px] rounded-lg overflow-hidden ${isFocused ? " border-teal-400" : "border-teal-100"}`}
    >
      <div className="h-[3rem] ">
        <textarea
          className="w-full h-full resize-none focus:outline-none"
          placeholder="답글 수정..."
          value={newContent}
          onChange={handleChange}
          onFocus={() => setIsFocused(true)}
          onBlur={() => setIsFocused(false)}
        />
      </div>
      <div className="flex justify-between items-center h-[2rem]">
        <div>
          <span className="text-sm text-slate-400">
            {newContent.length}/{lengthLimit}
          </span>
        </div>
        <div className="flex gap-[0.25rem]">
          <button
            className="px-4 py-1 text-sm text-slate-400 bg-gray-200 rounded-full"
            type="button"
            onClick={() => handleClose()}
          >
            취소
          </button>
          <ButtonWithAuth
            className="px-4 py-1 text-sm text-white bg-teal-400 rounded-full"
            type="button"
            onClick={() => handleEdit()}
          >
            수정
          </ButtonWithAuth>
        </div>
      </div>
    </div>
  );
}

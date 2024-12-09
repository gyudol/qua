"use client";

import type { ChangeEvent, Dispatch, SetStateAction } from "react";
import { useState } from "react";
import { toast } from "sonner";
import { usePutFeedCommentMutation } from "@/hooks/comment-service";
import { ButtonWithAuth } from "@/components/common/atoms";

interface CommentEditInputProps {
  commentUuid: string;
  content: string;
  setIsEditing: Dispatch<SetStateAction<boolean>>;
}

export function CommentEditInput({
  commentUuid,
  content,
  setIsEditing,
}: CommentEditInputProps) {
  const mutation = usePutFeedCommentMutation({ commentUuid });

  const lengthLimit = 1000;
  const [newContent, setNewContent] = useState(content);
  const [isFocused, setIsFocused] = useState<boolean>(false);

  function handleChange(e: ChangeEvent<HTMLTextAreaElement>) {
    setNewContent(e.target.value.slice(0, lengthLimit));
  }

  function handleClose() {
    setIsEditing(false);
  }

  function handleEdit() {
    if (newContent === "") return toast.error("내용을 입력해주세요");
    mutation.mutate(newContent);
    setNewContent("");
    setIsEditing(false);
  }

  return (
    <div
      className={`w-full p-[0.25rem] bg-white ring-2 ${isFocused ? " ring-teal-400" : "ring-teal-100"}`}
    >
      <div className="h-[3rem]">
        <textarea
          className="w-full h-full resize-none focus:outline-none"
          placeholder="댓글 수정..."
          value={newContent}
          onChange={handleChange}
          onFocus={() => setIsFocused(true)}
          onBlur={() => setIsFocused(false)}
          spellCheck={false}
        />
      </div>
      <div className="flex justify-between items-center h-[2rem] ">
        <div>
          <span className="text-sm text-slate-400">
            {newContent.length}/{lengthLimit}
          </span>
        </div>
        <div className="flex gap-[0.25rem]">
          <button
            className="px-4 py-1 text-sm text-slate-400 bg-slate-200 rounded-full"
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

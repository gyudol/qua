"use client";

import { useSession } from "next-auth/react";
import type { ChangeEvent, Dispatch, SetStateAction } from "react";
import { useState } from "react";
import { putFeedComment } from "@/actions/comment-service";
import { alertNotImplemented } from "@/functions/utils";
import type { FeedComment } from "@/types/comment/comment-read-service";

interface CommentEditInputProps {
  commentUuid: string;
  content: string;
  setIsEditing: Dispatch<SetStateAction<boolean>>;
  setComment: Dispatch<SetStateAction<FeedComment>>;
}

export function CommentEditInput({
  commentUuid,
  content,
  setIsEditing,
  setComment,
}: CommentEditInputProps) {
  const { status } = useSession();

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
    if (newContent === "") {
      alertNotImplemented({ message: "내용을 입력해주세요" });
    } else if (status === "authenticated") {
      void putFeedComment({
        commentUuid,
        content: newContent,
      }).then((_) => {
        setComment((prevComment) => ({ ...prevComment, content: newContent }));
        setNewContent("");
        setIsEditing(false);
      });
    } else if (status === "unauthenticated") {
      alertNotImplemented({ message: "로그인 하세요" });
    }
  }

  return (
    <div className={`w-full ${isFocused ? "ring-2 ring-blue-500" : ""}`}>
      <div className="h-[3rem]">
        <textarea
          className="w-full h-full resize-none focus:outline-none"
          placeholder="답글 추가..."
          value={newContent}
          onChange={handleChange}
          onFocus={() => setIsFocused(true)}
          onBlur={() => setIsFocused(false)}
        />
      </div>
      <div className="flex justify-between items-center h-[2rem] bg-white">
        <div>
          <span className="text-sm text-gray-500">
            {newContent.length}/{lengthLimit}
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
            onClick={() => handleEdit()}
            disabled={status === "loading" || !content}
          >
            작성
          </button>
        </div>
      </div>
    </div>
  );
}

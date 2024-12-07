"use client";

import type { Dispatch, SetStateAction } from "react";

interface ReplyButtonProps {
  setIsReplyInputShowed: Dispatch<SetStateAction<boolean>>;
}

export function ReplyButton({ setIsReplyInputShowed }: ReplyButtonProps) {
  function handleOpen() {
    setIsReplyInputShowed(true);
  }

  return (
    <button
      type="button"
      onClick={() => handleOpen()}
      className="px-4 py-1 text-sm text-white bg-teal-400 rounded-full"
    >
      답글
    </button>
  );
}

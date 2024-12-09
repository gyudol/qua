'use client';

import type { Dispatch, SetStateAction } from 'react';

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
      className="px-4 py-1 text-xs bg-white text-teal-400 border-[1px] border-teal-400 rounded-full"
    >
      답글
    </button>
  );
}

'use client';

import type { ChangeEvent } from 'react';
import { useState } from 'react';
import { usePostFeedCommentMutation } from '@/hooks/comment-service';
import { useSessionContext } from '@/context/SessionContext';
import { ButtonWithAuth } from '@/components/common/atoms';

interface CommentInputProps {
  feedUuid: string;
}

export function CommentInput({ feedUuid }: CommentInputProps) {
  const { memberUuid } = useSessionContext();
  const mutation = usePostFeedCommentMutation({ feedUuid });

  const lengthLimit = 1000;
  const [content, setContent] = useState<string>('');
  const [isFocused, setIsFocused] = useState<boolean>(false);

  function handleChange(e: ChangeEvent<HTMLTextAreaElement>) {
    setContent(e.target.value.slice(0, lengthLimit));
  }

  function handlePost() {
    if (!memberUuid) return;
    mutation.mutate({ content, memberUuid });
    setContent('');
    setIsFocused(false);
  }

  function handleCancle() {
    setContent('');
    setIsFocused(false);
  }

  return (
    <div
      className={`w-full p-[0.25rem] !rounded-lg border-[1px] ${isFocused ? ' border-teal-400' : 'border-teal-100'}`}
    >
      <div
        className={`transition-all duration-200 ${isFocused ? 'h-[8rem]' : 'h-[2rem]'}`}
      >
        <textarea
          className="w-full h-full resize-none focus:outline-none placeholder:text-sm text-sm p-2"
          placeholder="댓글 추가..."
          value={content}
          onChange={handleChange}
          onFocus={() => setIsFocused(true)}
          spellCheck={false}
        />
      </div>
      {isFocused ? (
        <div className="flex justify-between items-center">
          <p className="p-2 text-xs text-slate-400">
            {content.length} / {lengthLimit}
          </p>
          <div className="flex gap-[0.25rem] p-2">
            <button
              className="px-4 py-1 text-sm text-slate-400 bg-slate-200 !rounded-md"
              type="button"
              onClick={() => handleCancle()}
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
      ) : null}
    </div>
  );
}

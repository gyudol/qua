"use client";

import { cn } from "@repo/ui/lib/utils";
import { useState } from "react";
import { useMutation } from "@tanstack/react-query";
import { ButtonWithAuth } from "@/components/common/atoms";
import type { PostCommentParam, TargetType } from "@/types/comment-service";
import { PostComment } from "@/actions/comment-service";

type CommentInputProp<T extends TargetType, IsRecomment extends boolean> = Omit<
  PostCommentParam<T, IsRecomment>,
  "body"
>;

export default function CommentInput<
  T extends TargetType,
  IsRecomment extends boolean,
>({
  targetType,
  isRecomment,
  feedUuid,
  shortsUuid,
  commentUuid,
}: CommentInputProp<T, IsRecomment>) {
  const [isFocused, setIsFocused] = useState(false);
  const [content, setContent] = useState("");

  const memberUuid = "member-001";

  const addComment = useMutation({
    mutationFn: (newContent: string) =>
      PostComment<T, IsRecomment>({
        targetType,
        isRecomment,
        feedUuid,
        shortsUuid,
        commentUuid,
        body: { memberUuid, content: newContent },
      }),
  });

  function handleSubmit() {
    addComment.mutate(content);
  }

  return (
    <div
      className={cn(
        "relative",
        isFocused ? "h-32" : "h-12",
        "transition-all duration-200",
      )}
    >
      <textarea
        className={cn(
          "w-full h-full px-4 py-2 bg-gray-50 rounded-lg",
          "focus:outline-none focus:ring-2 focus:ring-blue-500",
          "resize-none",
        )}
        placeholder="댓글 추가..."
        value={content}
        onChange={(e) => setContent(e.target.value)}
        onFocus={() => setIsFocused(true)}
      />
      {isFocused || content ? (
        <div className="absolute bottom-2 right-2 flex gap-2">
          <button
            type="button"
            className={cn(
              "px-4 py-1 text-sm text-gray-500 bg-gray-200 rounded-full",
              "hover:bg-gary-300",
            )}
            onClick={() => {
              setIsFocused(false);
              setContent("");
            }}
          >
            취소
          </button>
          <ButtonWithAuth
            type="button"
            className={cn(
              "px-4 py-1 text-sm text-white bg-blue-500 rounded-full",
              "hover:bg-blue-600",
              "text-sm text-white",
            )}
            onClick={() => {
              handleSubmit();
              setIsFocused(false);
              setContent("");
            }}
          >
            작성
          </ButtonWithAuth>
        </div>
      ) : null}
    </div>
  );
}

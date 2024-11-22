"use client";
import { createContext, useContext } from "react";
import type { TargetType } from "@/types/comment-service";

export interface CommentTargetType {
  targetType?: TargetType;
  targetUuid?: string;
}

export interface CommentDrawerData {
  commentTarget?: CommentTargetType;
  setCommentTarget?: React.Dispatch<React.SetStateAction<CommentTargetType>>;
  open?: boolean;
  setOpen?: React.Dispatch<React.SetStateAction<boolean>>;
}

export const CommentDrawerContext = createContext<CommentDrawerData>(
  {} as CommentDrawerData,
);

export const useCommentDrawerContext = () => useContext(CommentDrawerContext);

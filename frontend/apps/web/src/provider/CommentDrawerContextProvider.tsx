"use client";

import { useState } from "react";
import type { CommentTargetType } from "@/context/DrawerContext";
import { CommentDrawerContext } from "@/context/DrawerContext";

export function CommentDrawerContextProvider({
  children,
  defaultValue = { targetUuid: "" },
}: {
  children: React.ReactNode;
  defaultValue?: CommentTargetType;
}) {
  const [isDrawerOpen, setIsDrawerOpen] = useState(false);
  const [commentTarget, setCommentTarget] =
    useState<CommentTargetType>(defaultValue);
  return (
    <CommentDrawerContext.Provider
      value={{
        commentTarget,
        setCommentTarget,
        open: isDrawerOpen,
        setOpen: setIsDrawerOpen,
      }}
    >
      {children}
    </CommentDrawerContext.Provider>
  );
}

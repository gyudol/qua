"use client";
import type { PropsWithChildren } from "react";
import { useState } from "react";
import { FeedCommentDrawerContext } from "@/context/FeedCommentDrawerContext";

export function FeedCommentDrawerContextProvider({
  children,
}: PropsWithChildren) {
  const [isDrawerOpen, setIsDrawerOpen] = useState(false);
  return (
    <FeedCommentDrawerContext.Provider
      value={{
        open: isDrawerOpen,
        setOpen: setIsDrawerOpen,
      }}
    >
      {children}
    </FeedCommentDrawerContext.Provider>
  );
}

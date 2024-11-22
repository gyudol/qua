import type { PropsWithChildren } from "react";
import { useState } from "react";
import type { CommentTargetType } from "@/context/DrawerContext";
import { CommentDrawerContext } from "@/context/DrawerContext";

export function CommentDrawerContextProvider({ children }: PropsWithChildren) {
  const [isDrawerOpen, setIsDrawerOpen] = useState(false);
  const [commentTarget, setCommentTarget] = useState<CommentTargetType>({
    targetUuid: "",
  });
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

"use client";

import {
  Drawer,
  DrawerContent,
  DrawerDescription,
  DrawerHeader,
  DrawerTitle,
} from "@repo/ui/shadcn/drawer";
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogHeader,
  DialogTitle,
} from "@repo/ui/shadcn/dialog";
import { VisuallyHidden } from "@radix-ui/react-visually-hidden";
import { useCommentDrawerContext } from "@/context/DrawerContext";
import { useMediaQuery } from "@/hooks/useMediaQuery";
import { CommentSection } from "@/components/shorts-comment-section/templates";

export default function ShortsCommentDrawer() {
  const { open, commentTarget, setOpen } = useCommentDrawerContext();
  const isDesktop = useMediaQuery("(min-width: 768px)");

  if (isDesktop)
    return (
      <Dialog
        open={open}
        onOpenChange={() => {
          if (setOpen) setOpen((prev) => !prev);
        }}
      >
        <DialogContent className="min-h-[30rem]">
          <VisuallyHidden>
            <DialogHeader>
              <DialogTitle>댓글창</DialogTitle>
              <DialogDescription>{commentTarget?.targetUuid}</DialogDescription>
            </DialogHeader>
          </VisuallyHidden>

          <CommentSection
            {...{ shortsUuid: commentTarget?.targetUuid || "" }}
          />
        </DialogContent>
      </Dialog>
    );

  return (
    <Drawer
      open={open}
      onOpenChange={() => {
        if (setOpen) setOpen((prev) => !prev);
      }}
    >
      <DrawerContent className="w-full max-w-[600px] h-[50%] mx-auto">
        <VisuallyHidden>
          <DrawerHeader>
            <DrawerTitle>댓글창</DrawerTitle>
            <DrawerDescription>{commentTarget?.targetUuid}</DrawerDescription>
          </DrawerHeader>
        </VisuallyHidden>
        <CommentSection {...{ shortsUuid: commentTarget?.targetUuid || "" }} />
      </DrawerContent>
    </Drawer>
  );
}

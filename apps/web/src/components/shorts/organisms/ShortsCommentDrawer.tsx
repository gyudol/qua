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
import { useCommentDrawerContext } from "@/context/DrawerContext";
import { useMediaQuery } from "@/hooks/useMediaQuery";

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
        <DialogContent className="w-full max-w-[600px] h-[50%] mx-auto p-20">
          <DialogHeader>
            <DialogTitle>댓글창</DialogTitle>
            <DialogDescription>{commentTarget?.targetUuid}</DialogDescription>
          </DialogHeader>
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
        <DrawerHeader>
          <DrawerTitle>댓글창</DrawerTitle>
          <DrawerDescription>{commentTarget?.targetUuid}</DrawerDescription>
        </DrawerHeader>
        {commentTarget?.targetUuid}
      </DrawerContent>
    </Drawer>
  );
}

"use client";
import {
  Drawer,
  DrawerContent,
  DrawerDescription,
  DrawerHeader,
  DrawerTitle,
  DrawerTrigger,
} from "@repo/ui/shadcn/drawer";
import { VisuallyHidden } from "@radix-ui/react-visually-hidden";
import { useFeedCommentDrawerContext } from "@/context/FeedCommentDrawerContext";
import { CommentSection } from "../templates";

function CommentDrawer({ feedUuid }: { feedUuid: string }) {
  const { open, setOpen } = useFeedCommentDrawerContext();
  return (
    <>
      {/* <div className="w-full h-[4rem]" /> */}
      <Drawer
        open={open}
        onOpenChange={(changedOpen) => {
          if (setOpen) setOpen(changedOpen);
        }}
      >
        <DrawerTrigger asChild>
          <button
            type="button"
            className="
          w-full md:max-w-md z-50
          text-white text- font-bold
          h-[4rem] absolute bottom-[0] bg-teal-300 !rounded-t-xl border-[1px] !border-teal-400"
          >
            댓글 작성하기
          </button>
        </DrawerTrigger>

        <DrawerContent className="max-h-[80%]" aria-hidden="false">
          <VisuallyHidden>
            <DrawerHeader>
              <DrawerTitle>댓글창</DrawerTitle>
              <DrawerDescription>댓글창 입니다.</DrawerDescription>
            </DrawerHeader>
          </VisuallyHidden>
          <CommentSection {...{ feedUuid }} />
        </DrawerContent>
      </Drawer>
    </>
  );
}

export default CommentDrawer;

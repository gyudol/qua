"use client";
import {
  Drawer,
  DrawerContent,
  DrawerDescription,
  DrawerHeader,
  DrawerTitle,
  DrawerTrigger,
} from "@repo/ui/shadcn/drawer";
import { Button } from "@repo/ui/shadcn/button";
import { VisuallyHidden } from "@radix-ui/react-visually-hidden";
import { CommentSection } from "../templates";

function CommentDrawer({ feedUuid }: { feedUuid: string }) {
  return (
    <Drawer>
      <DrawerTrigger asChild>
        <Button
          variant="default"
          className="w-full md:max-w-md py-[1.7rem] fixed bottom-0 bg-teal-300 !rounded-t-xl border-[1px] !border-teal-400"
        >
          댓글 작성하기
        </Button>
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
  );
}

export default CommentDrawer;

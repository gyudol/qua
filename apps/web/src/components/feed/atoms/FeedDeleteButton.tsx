"use client";

import { Trash2 } from "lucide-react";
import { toast } from "sonner";
import type { Feed } from "@/types/contents";
import { ButtonWithAuth } from "../../common/atoms";

type FeedDeleteButtonProps = Pick<Feed, "feedUuid">;

export function FeedDeleteButton({ feedUuid: _ }: FeedDeleteButtonProps) {
  function handleClick() {
    toast.error("해당 게시글은 삭제되었습니다.(아님)");
  }

  return (
    <ButtonWithAuth
      type="button"
      className="w-full px-4 py-2 text-left text-red-600 hover:bg-gray-50 flex items-center gap-2"
      onClick={handleClick}
    >
      <Trash2 className="w-4 h-4" />
      삭제하기
    </ButtonWithAuth>
  );
}

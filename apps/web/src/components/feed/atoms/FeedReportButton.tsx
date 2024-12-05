"use client";

import { Flag } from "lucide-react";
import { toast } from "sonner";
import type { Feed } from "@/types/feed/feed-read-service";
import { ButtonWithAuth } from "@/components/common/atoms";

type FeedReportButtonProps = Pick<Feed, "feedUuid">;

export function FeedReportButton({ feedUuid }: FeedReportButtonProps) {
  function handleClick() {
    toast.success("해당 게시글을 신고하였습니다.(아님)", {
      description: feedUuid,
    });
  }

  return (
    <ButtonWithAuth
      type="button"
      className="w-full px-4 py-2 text-left hover:bg-gray-50 flex items-center gap-2"
      onClick={handleClick}
    >
      <span>
        <Flag className="w-4 h-4" />
      </span>
      <span>신고하기</span>
    </ButtonWithAuth>
  );
}

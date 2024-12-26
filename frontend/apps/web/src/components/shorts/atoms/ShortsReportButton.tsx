"use client";

import { Flag } from "lucide-react";
import { toast } from "sonner";
import type { Shorts } from "@/types/shorts/shorts-read-service";
import { ButtonWithAuth } from "@/components/common/atoms";

type ShortsReportButtonProps = Pick<Shorts, "shortsUuid">;

export function ShortsReportButton({ shortsUuid }: ShortsReportButtonProps) {
  function handleClick() {
    toast.error("해당 쇼츠를 신고하였습니다.", {
      description: shortsUuid,
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

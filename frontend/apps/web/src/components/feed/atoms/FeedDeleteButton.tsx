"use client";

import { Trash2 } from "lucide-react";
import { useRouter, useSearchParams } from "next/navigation";
import type { Feed } from "@/types/contents";
import { deleteFeed } from "@/actions/feed-service";
import { useDeleteFeed } from "@/hooks";
import { toURLSearchParams } from "@/functions/utils";
import { ButtonWithAuth } from "../../common/atoms";

type FeedDeleteButtonProps = Pick<Feed, "feedUuid">;

export function FeedDeleteButton({ feedUuid }: FeedDeleteButtonProps) {
  const router = useRouter();
  const searchParams = Object.fromEntries(useSearchParams().entries());
  const { mutate } = useDeleteFeed({ feedUuid });

  function handleClick() {
    void deleteFeed({ feedUuid }).then(() => {
      mutate();
      router.push(`?${toURLSearchParams({ ...searchParams })}`);
    });
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

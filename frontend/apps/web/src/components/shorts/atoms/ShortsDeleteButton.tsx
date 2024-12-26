"use client";

import { Trash2 } from "lucide-react";
import { useRouter, useSearchParams } from "next/navigation";
import type { Shorts } from "@/types/contents";
import { deleteShorts } from "@/actions/shorts-service";
import { toURLSearchParams } from "@/functions/utils";
import { useDeleteShorts } from "@/hooks/shorts-service";
import { ButtonWithAuth } from "../../common/atoms";

type ShortsDeleteButtonProps = Pick<Shorts, "shortsUuid">;

export function ShortsDeleteButton({ shortsUuid }: ShortsDeleteButtonProps) {
  const router = useRouter();
  const searchParams = Object.fromEntries(useSearchParams().entries());
  const { mutate } = useDeleteShorts({ shortsUuid });

  function handleClick() {
    void deleteShorts({ shortsUuid }).then(() => {
      mutate();
      router.push(`/shorts?${toURLSearchParams({ ...searchParams })}`);
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

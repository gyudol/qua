"use client";

import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from "@repo/ui/shadcn/dropdown-menu";
import { Flag, MoreHorizontal, Pencil, Trash2 } from "lucide-react";
import type { Dispatch, SetStateAction } from "react";
import { toast } from "sonner";
import { useDeleteShortsRecommentMutation } from "@/hooks";
import { useSessionContext } from "@/context/SessionContext";
import { ButtonWithAuth } from "@/components/common/atoms";

interface RecommentMoreButtonProps {
  recommentUuid: string;
  memberUuid: string;
  setIsEditing: Dispatch<SetStateAction<boolean>>;
}

export function RecommentMoreButton({
  recommentUuid,
  memberUuid,
  setIsEditing,
}: RecommentMoreButtonProps) {
  const { memberUuid: sessionUuid } = useSessionContext();
  const deleteMutation = useDeleteShortsRecommentMutation({ recommentUuid });

  function handleReport() {
    toast.error("해당 답글을 신고했습니다.");
  }
  function handleEdit() {
    setIsEditing(true);
  }
  function handleDelete() {
    deleteMutation.mutate();
  }

  return (
    <DropdownMenu>
      <DropdownMenuTrigger>
        <button type="button" className="p-1 hover:bg-gray-100 rounded-full">
          <MoreHorizontal className="w-5 h-5" />
        </button>
      </DropdownMenuTrigger>
      <DropdownMenuContent>
        {sessionUuid !== memberUuid ? (
          <DropdownMenuItem>
            <ButtonWithAuth
              type="button"
              className="w-full px-4 py-2 text-left flex items-center gap-2"
              onClick={() => handleReport()}
            >
              <Flag className="w-4 h-4" />
              신고하기
            </ButtonWithAuth>
          </DropdownMenuItem>
        ) : (
          <>
            <DropdownMenuItem>
              <ButtonWithAuth
                className="w-full px-4 py-2 text-left flex items-center gap-2"
                onClick={handleEdit}
              >
                <Pencil className="w-4 h-4" />
                수정하기
              </ButtonWithAuth>
            </DropdownMenuItem>
            <DropdownMenuItem>
              <ButtonWithAuth
                className="w-full px-4 py-2 text-left text-red-600 hover:bg-gray-50 flex items-center gap-2"
                onClick={handleDelete}
              >
                <Trash2 className="w-4 h-4" />
                삭제하기
              </ButtonWithAuth>
            </DropdownMenuItem>
          </>
        )}
      </DropdownMenuContent>
    </DropdownMenu>
  );
}

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
import { useDeleteShortsCommentMutation } from "@/hooks/comment-service";
import { useSessionContext } from "@/context/SessionContext";
import { ButtonWithAuth } from "@/components/common/atoms";

interface CommentMoreButtonProps {
  commentUuid: string;
  memberUuid: string;
  setIsEditing: Dispatch<SetStateAction<boolean>>;
}

export function CommentMoreButton({
  commentUuid,
  memberUuid,
  setIsEditing,
}: CommentMoreButtonProps) {
  const { memberUuid: sessionUuid } = useSessionContext();
  const deleteMutation = useDeleteShortsCommentMutation({ commentUuid });

  function handleReport() {
    toast.error("해당 댓글을 신고하였습니다.");
  }
  function handleEdit() {
    setIsEditing(true);
  }
  function handleDelete() {
    deleteMutation.mutate();
  }
  return (
    <DropdownMenu>
      <DropdownMenuTrigger asChild>
        <button type="button" className="p-1 hover:bg-gray-50 rounded-full">
          <MoreHorizontal size={16} color="gray" />
        </button>
      </DropdownMenuTrigger>
      <DropdownMenuContent align="end" className="rounded-lg">
        {sessionUuid !== memberUuid ? (
          <DropdownMenuItem>
            <ButtonWithAuth
              className="w-full px-4 py-1 text-left flex items-center gap-2 text-xs"
              onClick={() => handleReport()}
            >
              <Flag size={12} />
              신고하기
            </ButtonWithAuth>
          </DropdownMenuItem>
        ) : (
          <>
            <DropdownMenuItem>
              <ButtonWithAuth
                className="w-full px-4 py-1 text-left flex items-center gap-2 text-xs"
                onClick={handleEdit}
              >
                <Pencil size={12} />
                수정하기
              </ButtonWithAuth>
            </DropdownMenuItem>
            <DropdownMenuItem>
              <ButtonWithAuth
                className="w-full px-4 py-2 text-left text-red-600 flex items-center gap-2 text-xs"
                onClick={handleDelete}
              >
                <Trash2 size={12} />
                삭제하기
              </ButtonWithAuth>
            </DropdownMenuItem>
          </>
        )}
      </DropdownMenuContent>
    </DropdownMenu>
  );
}

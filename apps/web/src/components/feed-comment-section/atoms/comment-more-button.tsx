"use client";

import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from "@repo/ui/shadcn/dropdown-menu";
import { Flag, MoreHorizontal, Pencil, Trash2 } from "lucide-react";
import { useSession } from "next-auth/react";
import type { Dispatch, SetStateAction } from "react";
import { alertNotImplemented } from "@/functions/utils";
import { useDeleteFeedCommentMutation } from "@/hooks/comment-service";

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
  const { status, data } = useSession();
  const deleteMutation = useDeleteFeedCommentMutation({ commentUuid });

  if (status !== "authenticated") {
    return (
      <button
        type="button"
        className="p-1 hover:bg-gray-100 rounded-full"
        onClick={() => alertNotImplemented({ message: "로그인 해주세요." })}
      >
        <MoreHorizontal className="w-5 h-5" />
      </button>
    );
  }

  const session = data as { user: { memberUuid: string } };

  function handleReport() {
    alertNotImplemented();
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
        {session.user.memberUuid !== memberUuid ? (
          <DropdownMenuItem>
            <button
              type="button"
              className="w-full px-4 py-2 text-left hover:bg-gray-50 flex items-center gap-2"
              onClick={() => handleReport()}
            >
              <Flag className="w-4 h-4" />
              신고하기
            </button>
          </DropdownMenuItem>
        ) : (
          <>
            <DropdownMenuItem>
              <button
                type="button"
                className="w-full px-4 py-2 text-left hover:bg-gray-50 flex items-center gap-2"
                onClick={handleEdit}
              >
                <Pencil className="w-4 h-4" />
                수정하기
              </button>
            </DropdownMenuItem>
            <DropdownMenuItem>
              <button
                type="button"
                className="w-full px-4 py-2 text-left text-red-600 hover:bg-gray-50 flex items-center gap-2"
                onClick={handleDelete}
              >
                <Trash2 className="w-4 h-4" />
                삭제하기
              </button>
            </DropdownMenuItem>
          </>
        )}
      </DropdownMenuContent>
    </DropdownMenu>
  );
}

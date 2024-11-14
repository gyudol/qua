"use client";

import {
  Flag,
  Heart,
  MessageSquare,
  MoreHorizontal,
  Pencil,
  Trash2,
} from "lucide-react";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from "@repo/ui/shadcn/dropdown-menu";
import { useMutation, useQuery } from "@tanstack/react-query";
import { useState } from "react";
import { PostedAt } from "@/components/common/atoms";
import Profile from "@/components/profile/molecules/Profile";
import { alertNotImplemented } from "@/functions/utils";
import type { Member } from "@/types/member";
import type { CommentReqParam, TargetType } from "@/types/comment-service";
import { DeleteComment, GetComment } from "@/actions/comment-service";

type CommentProp<
  T extends TargetType,
  IsRecomment extends boolean,
> = CommentReqParam<T, IsRecomment>;

export default function Comment<
  T extends TargetType,
  IsRecomment extends boolean,
>({
  targetType,
  isRecomment,
  commentUuid,
  recommentUuid,
}: CommentProp<T, IsRecomment>) {
  const deleteComment = useMutation({
    mutationFn: () =>
      DeleteComment<T, IsRecomment>({
        targetType,
        isRecomment,
        commentUuid,
      }),
  });

  const [isDeleted, setIsDeleted] = useState(false);

  const commentUuidKey = `${targetType}__${isRecomment ? "recomment" : "comment"}--${commentUuid || recommentUuid}`;

  const { data: comment, isLoading } = useQuery({
    queryKey: ["comment", commentUuidKey],
    queryFn: async () =>
      GetComment<T, IsRecomment>({
        targetType,
        isRecomment,
        commentUuid,
        recommentUuid,
      }),
  });

  // const { data: memberDetails, isLoading: isDetailsLoading, isError: isDetailsError } = useQuery(
  //   ['memberDetails', memberUuid],
  //   () => fetchMemberDetails(memberUuid),
  //   {
  //     enabled: !!memberUuid,  // memberUuid가 있을 때만 요청
  //   }
  // );

  // const comment: Omit<CommentType<T, IsRecomment>, "commentUuid"> =
  //   {
  //     memberUuid: "member-001",
  //     content: "물코기는 사랑데스",
  //     createdAt: "2024-01-01 00:00:00",
  //     updatedAt: "2024-01-01 00:00:00",
  //   };

  const memberUuid = comment?.memberUuid || "member-001";

  const { profileImageUrl, nickname }: Member = {
    memberUuid,
    profileImageUrl: `/dummies/members/${comment?.memberUuid}.png`,
    nickname: "나만없어_fish",
    grade: "",
  };

  const profileUrl = `/profile/${memberUuid}`;

  if (isLoading) return <div>불러오는 중</div>;

  // eslint-disable-next-line @typescript-eslint/no-non-null-assertion -- must exist
  const { createdAt } = comment!;

  function handleDelete() {
    deleteComment.mutate();
    setIsDeleted(true);
  }

  if (isDeleted) return <div>삭제되었습니다</div>;

  return (
    <div className="flex gap-2 py-4">
      <Profile.PictureWithLink
        href={profileUrl}
        src={profileImageUrl}
        alt={nickname}
      />
      <div className="w-full flex flex-col gap-[4px]">
        <div className="w-full flex items-center justify-between">
          <div className="flex items-center gap-2">
            <Profile.NameWithLink href={profileUrl} nickname={nickname} />
            &#183;
            <PostedAt postedAt={createdAt} />
          </div>
          <DropdownMenu>
            <DropdownMenuTrigger>
              <button
                type="button"
                className="p-1 hover:bg-gray-100 rounded-full"
              >
                <MoreHorizontal className="w-5 h-5" />
              </button>
            </DropdownMenuTrigger>
            <DropdownMenuContent>
              <DropdownMenuItem>
                <button
                  type="button"
                  className="w-full px-4 py-2 text-left hover:bg-gray-50 flex items-center gap-2"
                >
                  <Flag className="w-4 h-4" />
                  신고하기
                </button>
              </DropdownMenuItem>
              <DropdownMenuItem>
                <button
                  type="button"
                  className="w-full px-4 py-2 text-left hover:bg-gray-50 flex items-center gap-2"
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
            </DropdownMenuContent>
          </DropdownMenu>
        </div>

        <div>{comment?.content}</div>
        <div className="flex items-center gap-[15px]">
          <button
            type="button"
            className="flex items-center gap-[8px]"
            onClick={() => alertNotImplemented()}
          >
            <Heart />
            <span>99</span>
          </button>

          {isRecomment ? null : (
            <button
              type="button"
              className="flex items-center gap-[8px]"
              onClick={() => alertNotImplemented()}
            >
              <MessageSquare />
              <span>99</span>
            </button>
          )}

          {isRecomment ? null : (
            <button
              type="button"
              className="bg-[var(--theme-color)] text-white p-[5px_10px] rounded-full"
            >
              답글
            </button>
          )}
        </div>
      </div>
    </div>
  );
}

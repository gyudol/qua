"use client";

import { useState } from "react";
import Link from "next/link";
import type { ShortsComment } from "@/types/comment/comment-read-service";
import { PostedAt } from "@/components/common/atoms";
import { useGetShortsCommentQuery } from "@/hooks";
import { useMemberCompactProfile } from "@/hooks/member-read-service";
import { MemberProfileImage } from "@/components/profile/atoms/MemberProfileImage";
import { CommentEditInput, CommentMoreButton } from "../atoms";
import { CommentButtonGroup } from "../molecules";
import { RecommentViewList } from "./recomment-view-list";

interface CommentViewProps extends ShortsComment {
  justNow?: boolean;
}

export function CommentView({
  commentUuid,
  memberUuid,
  likeCount,
  dislikeCount,
  recommentCount,
  justNow,
}: CommentViewProps) {
  const [isEditing, setIsEditing] = useState<boolean>(false);
  const { data: comment } = useGetShortsCommentQuery({ commentUuid });
  const { data: memberProfile } = useMemberCompactProfile({ memberUuid });

  if (!comment || !memberProfile) return null;

  const { content, createdAt, updatedAt } = comment;
  const { profileImageUrl, nickname } = memberProfile;

  return (
    <div
      className={`mb-[0.5rem] p-[0.25rem] rounded-lg ${justNow ? "bg-teal-100" : ""}`}
    >
      <div className="flex">
        <div className="w-[2.5rem] mr-[1rem]">
          <MemberProfileImage
            {...{
              profileImageUrl,
              nickname,
              size: "2.5rem",
            }}
            link
          />
        </div>

        <div className="flex-1 mb-[0.5rem]">
          <div className="mb-[0.25rem] text-sm">
            <Link
              href={`/profile/${nickname}`}
              className="mr-[0.5rem] text-sm text-slate-600"
            >
              {nickname}
            </Link>
            <span className="text-nowrap">
              <PostedAt {...{ createdAt, updatedAt }} />
            </span>
          </div>
          {isEditing ? (
            <div>
              <CommentEditInput {...{ commentUuid, content, setIsEditing }} />
            </div>
          ) : (
            <>
              <div className="mb-[0.5rem] text-slate-700">{content}</div>
              <CommentButtonGroup
                {...{ commentUuid, likeCount, dislikeCount }}
              />
            </>
          )}
        </div>
        {!isEditing ? (
          <div className="w-[2.5rem]">
            <CommentMoreButton {...{ commentUuid, memberUuid, setIsEditing }} />
          </div>
        ) : null}
      </div>
      <RecommentViewList
        {...{
          commentUuid,
          recommentCount,
        }}
      />
    </div>
  );
}

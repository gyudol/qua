"use client";

import { useState } from "react";
import type { FeedComment } from "@/types/comment/comment-read-service";
import { Profile } from "@/components/@legacy-profile/molecules";
import { PostedAt } from "@/components/common/atoms";
import { useGetFeedCommentQuery } from "@/hooks";
import { useMemberCompactProfile } from "@/hooks/member-read-service";
import { CommentEditInput, CommentMoreButton } from "../atoms";
import { CommentButtonGroup } from "../molecules";
import { RecommentViewList } from "./recomment-view-list";

type CommentViewProps = FeedComment;

export function CommentView({
  commentUuid,
  memberUuid,
  likeCount,
  dislikeCount,
  recommentCount,
}: CommentViewProps) {
  const [isEditing, setIsEditing] = useState<boolean>(false);
  const { data: comment } = useGetFeedCommentQuery({ commentUuid });
  const { data: memberProfile } = useMemberCompactProfile({ memberUuid });

  if (!comment) return null;

  const { content, createdAt, updatedAt } = comment;

  return (
    <div>
      <div className="flex">
        {isEditing ? (
          <CommentEditInput {...{ commentUuid, content, setIsEditing }} />
        ) : (
          <>
            <div className="w-[2.5rem] mr-[1rem]">
              <Profile.PictureSkeleton />
            </div>
            <div className="flex-1">
              <div className="flex items-center">
                <span>{memberProfile?.nickname}</span>
                <span>
                  <PostedAt {...{ createdAt, updatedAt }} />
                </span>
              </div>
              <div>{content}</div>
              <CommentButtonGroup
                {...{ commentUuid, likeCount, dislikeCount }}
              />
            </div>
            <div className="w-[2.5rem]">
              <CommentMoreButton
                {...{ commentUuid, memberUuid, setIsEditing }}
              />
            </div>
          </>
        )}
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

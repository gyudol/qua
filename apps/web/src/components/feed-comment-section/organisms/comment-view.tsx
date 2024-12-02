"use client";

import { useState } from "react";
import { useQuery } from "@tanstack/react-query";
import type { FeedComment } from "@/types/comment/comment-read-service";
import { getMemberProfile } from "@/actions/member-read-service";
import { Profile } from "@/components/profile/molecules";
import { PostedAt } from "@/components/common/atoms";
import { useGetFeedCommentQuery } from "@/hooks";
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
  const { data: comment } = useGetFeedCommentQuery({ commentUuid });
  const { data: memberProfile } = useQuery({
    queryKey: ["member-profile", memberUuid],
    queryFn: () => getMemberProfile({ memberUuid }),
  });
  const [isEditing, setIsEditing] = useState<boolean>(false);

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

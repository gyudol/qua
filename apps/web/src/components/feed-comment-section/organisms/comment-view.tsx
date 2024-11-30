"use client";

import type { Dispatch, SetStateAction } from "react";
import { useState } from "react";
import { useQuery } from "@tanstack/react-query";
import type {
  FeedComment,
  FeedRecomment,
} from "@/types/comment/comment-read-service";
import { getMemberProfile } from "@/actions/member-read-service";
import { Profile } from "@/components/profile/molecules";
import { PostedAt } from "@/components/common/atoms";
import { CommentEditInput, CommentMoreButton } from "../atoms";
import { CommentButtonGroup } from "../molecules";

interface CommentViewProps extends FeedComment {
  setRecommentList: Dispatch<SetStateAction<FeedRecomment[]>>;
}

export function CommentView({
  setRecommentList,
  commentUuid,
  memberUuid,
  createdAt,
  updatedAt: _,
  content,
  likeCount,
  dislikeCount,
}: CommentViewProps) {
  const { data: memberProfile } = useQuery({
    queryKey: ["member-profile", memberUuid],
    queryFn: () => getMemberProfile({ memberUuid }),
  });

  const [isEditing, setIsEditing] = useState<boolean>(false);

  return (
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
                <PostedAt postedAt={createdAt} />
              </span>
            </div>
            <div>{content}</div>
            <CommentButtonGroup
              {...{ commentUuid, likeCount, dislikeCount, setRecommentList }}
            />
          </div>
          <div className="w-[2.5rem]">
            <CommentMoreButton {...{ commentUuid, memberUuid, setIsEditing }} />
          </div>
        </>
      )}
    </div>
  );
}

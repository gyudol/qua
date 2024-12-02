"use client";

import { useState } from "react";
import { useQuery } from "@tanstack/react-query";
import { getMemberProfile } from "@/actions/member-read-service";
import { PostedAt } from "@/components/common/atoms";
import { Profile } from "@/components/@legacy-profile/molecules";
import type { FeedRecomment } from "@/types/comment/comment-read-service";
import { useGetFeedRecommentQuery } from "@/hooks";
import { RecommentButtonGroup } from "../molecules";
import { RecommentEditInput, RecommentMoreButton } from "../atoms";

type RecommentViewProps = FeedRecomment;

export function RecommentView({
  memberUuid,
  commentUuid,
  recommentUuid,
  likeCount,
  dislikeCount,
}: RecommentViewProps) {
  const [isEditing, setIsEditing] = useState<boolean>(false);
  const { data: recomment } = useGetFeedRecommentQuery({ recommentUuid });

  const { data: memberProfile } = useQuery({
    queryKey: ["member-profile", memberUuid],
    queryFn: () => getMemberProfile({ memberUuid }),
  });

  if (!recomment) return null;

  const { content, createdAt, updatedAt } = recomment;

  return (
    <div className="flex">
      {isEditing ? (
        <RecommentEditInput {...{ recommentUuid, content, setIsEditing }} />
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
            <RecommentButtonGroup
              {...{
                commentUuid,
                recommentUuid,
                likeCount,
                dislikeCount,
              }}
            />
          </div>
          <div className="w-[2.5rem]">
            <RecommentMoreButton
              {...{ recommentUuid, memberUuid, setIsEditing }}
            />
          </div>
        </>
      )}
    </div>
  );
}

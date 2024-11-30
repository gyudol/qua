"use client";

import type { Dispatch, SetStateAction } from "react";
import { useState } from "react";
import { useQuery } from "@tanstack/react-query";
import { getMemberProfile } from "@/actions/member-read-service";
import { PostedAt } from "@/components/common/atoms";
import { Profile } from "@/components/profile/molecules";
import type { FeedRecomment } from "@/types/comment/comment-read-service";
import { RecommentButtonGroup } from "../molecules";
import { RecommentEditInput, RecommentMoreButton } from "../atoms";

interface RecommentViewProps extends FeedRecomment {
  setRecommentList: Dispatch<SetStateAction<FeedRecomment[]>>;
}

export function RecommentView({
  setRecommentList,
  ...recommentData
}: RecommentViewProps) {
  const [recomment, setRecomment] = useState<FeedRecomment>(recommentData);
  const {
    commentUuid,
    recommentUuid,
    memberUuid,
    createdAt,
    updatedAt,
    content,
    likeCount,
    dislikeCount,
  } = recomment;
  const [isEditing, setIsEditing] = useState<boolean>(false);

  const { data: memberProfile } = useQuery({
    queryKey: ["member-profile", memberUuid],
    queryFn: () => getMemberProfile({ memberUuid }),
  });

  return (
    <div className="flex">
      {isEditing ? (
        <RecommentEditInput
          {...{ recommentUuid, content, setIsEditing, setRecomment }}
        />
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
                setRecommentList,
              }}
            />
          </div>
          <div className="w-[2.5rem]">
            <RecommentMoreButton
              {...{ recommentUuid, memberUuid, setRecommentList, setIsEditing }}
            />
          </div>
        </>
      )}
    </div>
  );
}

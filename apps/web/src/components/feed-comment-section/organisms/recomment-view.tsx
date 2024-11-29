"use client";

import type { Dispatch, SetStateAction } from "react";
import { useEffect, useState } from "react";
import { getMemberProfile } from "@/actions/member-read-service";
import { PostedAt } from "@/components/common/atoms";
import { Profile } from "@/components/profile/molecules";
import type { FeedRecomment } from "@/types/comment/comment-read-service";
import type { MemberProfile } from "@/types/member";
import { RecommentButtonGroup } from "../molecules";
import { RecommentEditInput, RecommentMoreButton } from "../atoms";

interface RecommentViewProps extends FeedRecomment {
  setRecommentList: Dispatch<SetStateAction<FeedRecomment[]>>;
}

export function RecommentView({
  setRecommentList,
  ...recomment
}: RecommentViewProps) {
  const [memberProfile, setMemberProfile] = useState<MemberProfile | null>(
    null,
  );
  const [
    {
      commentUuid,
      recommentUuid,
      memberUuid,
      createdAt,
      updatedAt: _,
      content,
      likeCount,
      dislikeCount,
    },
    setRecomment,
  ] = useState<FeedRecomment>(recomment);
  const [isEditing, setIsEditing] = useState<boolean>(false);

  useEffect(() => {
    void getMemberProfile({ memberUuid }).then((res) => setMemberProfile(res));
  }, [memberUuid]);

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
                <PostedAt postedAt={createdAt} />
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

"use client";

import type { Dispatch, SetStateAction } from "react";
import { useEffect, useState } from "react";
import type {
  FeedComment,
  FeedRecomment,
} from "@/types/comment/comment-read-service";
import type { MemberProfile } from "@/types/member/member-read-service";
import { getMemberProfile } from "@/actions/member-read-service";
import { Profile } from "@/components/profile/molecules";
import { PostedAt } from "@/components/common/atoms";
import { CommentEditInput, CommentMoreButton } from "../atoms";
import { CommentButtonGroup } from "../molecules";

interface CommentViewProps extends FeedComment {
  setCommentList: Dispatch<SetStateAction<FeedComment[]>>;
  setRecommentList: Dispatch<SetStateAction<FeedRecomment[]>>;
}

export function CommentView({
  setCommentList,
  setRecommentList,
  ...comment
}: CommentViewProps) {
  const [
    {
      commentUuid,
      memberUuid,
      createdAt,
      updatedAt: _,
      content,
      likeCount,
      dislikeCount,
    },
    setComment,
  ] = useState<FeedComment>(comment);
  const [memberProfile, setMemberProfile] = useState<MemberProfile | null>(
    null,
  );

  useEffect(() => {
    void getMemberProfile({ memberUuid }).then((res) => setMemberProfile(res));
  }, [memberUuid]);

  const [isEditing, setIsEditing] = useState<boolean>(false);

  return (
    <div className="flex">
      {isEditing ? (
        <CommentEditInput
          {...{ commentUuid, content, setIsEditing, setComment }}
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
            <CommentButtonGroup
              {...{ commentUuid, likeCount, dislikeCount, setRecommentList }}
            />
          </div>
          <div className="w-[2.5rem]">
            <CommentMoreButton
              {...{ commentUuid, memberUuid, setCommentList, setIsEditing }}
            />
          </div>
        </>
      )}
    </div>
  );
}

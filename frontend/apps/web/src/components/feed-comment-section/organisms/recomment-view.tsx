"use client";

import { useState } from "react";
import Link from "next/link";
import { PostedAt } from "@/components/common/atoms";
import type { FeedRecomment } from "@/types/comment/comment-read-service";
import { useGetFeedRecommentQuery } from "@/hooks";
import { useMemberCompactProfile } from "@/hooks/member-read-service";
import { MemberProfileImage } from "@/components/profile/atoms/MemberProfileImage";
import { RecommentButtonGroup } from "../molecules";
import { RecommentEditInput, RecommentMoreButton } from "../atoms";

interface RecommentViewProps extends FeedRecomment {
  justNow?: boolean;
}

export function RecommentView({
  memberUuid,
  commentUuid,
  recommentUuid,
  likeCount,
  dislikeCount,
  justNow,
}: RecommentViewProps) {
  const [isEditing, setIsEditing] = useState<boolean>(false);
  const { data: recomment } = useGetFeedRecommentQuery({ recommentUuid });
  const { data: memberProfile } = useMemberCompactProfile({ memberUuid });

  if (!recomment || !memberProfile) return null;

  const { profileImageUrl, nickname } = memberProfile;

  const { content, createdAt, updatedAt } = recomment;

  return (
    <div
      className={`flex p-[0.25rem] rounded-lg ${justNow ? "bg-teal-100" : ""}`}
    >
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
        <div className="mb-[0.25rem]">
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
          <RecommentEditInput {...{ recommentUuid, content, setIsEditing }} />
        ) : (
          <>
            <div className="mb-[0.5rem] text-slate-700">{content}</div>
            <RecommentButtonGroup
              {...{ commentUuid, recommentUuid, likeCount, dislikeCount }}
            />
          </>
        )}
      </div>{" "}
      {!isEditing ? (
        <div className="w-[2.5rem]">
          <RecommentMoreButton
            {...{ recommentUuid, memberUuid, setIsEditing }}
          />
        </div>
      ) : null}
    </div>
  );
}

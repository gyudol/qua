"use client";

import Link from "next/link";
import { PostedAt } from "@/components/common/atoms";
import { useMemberCompactProfile } from "@/hooks";
import type { Feed } from "@/types/feed/feed-read-service";
import { MemberProfileImage } from "@/components/profile/templates/atoms/MemberProfileImage";
import { FeedTitle } from "../atoms/FeedTitle";
import { FeedMoreOption } from "./FeedMoreOption";

interface FeedCompactHeaderProps
  extends Pick<
    Feed,
    | "feedUuid"
    | "memberUuid"
    | "createdAt"
    | "updatedAt"
    | "title"
    | "categoryName"
  > {
  link?: boolean;
}

export function FeedCompactHeader({
  feedUuid,
  memberUuid,
  createdAt,
  updatedAt,
  title,
  categoryName,
  link,
}: FeedCompactHeaderProps) {
  const { data: member, status } = useMemberCompactProfile({ memberUuid });
  if (status !== "success") return null;
  const profileImageUrl = member.profileImageUrl;
  const nickname = member.nickname;

  return (
    <header className="flex flex-col mb-[0.25rem]">
      <div className="flex justify-between items-center mb-[0.5rem]">
        <div className="flex items-center mr-[1rem]">
          <div className="mr-[1rem]">
            <MemberProfileImage
              {...{ size: "1.5rem", profileImageUrl, nickname }}
              link
            />
          </div>
          <Link href={`/profile/${nickname}`} className="mr-[1rem] text-nowrap">
            {nickname}
          </Link>
          <PostedAt {...{ createdAt, updatedAt }} />
        </div>
        <div>
          <FeedMoreOption {...{ feedUuid, memberUuid }} />
        </div>
      </div>
      <FeedTitle {...{ feedUuid, title, categoryName, link }} />
    </header>
  );
}

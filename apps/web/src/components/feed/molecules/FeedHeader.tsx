"use client";

import Link from "next/link";
import { PostedAt } from "@/components/common/atoms";
import { useMemberCompactProfile } from "@/hooks";
import type { Feed } from "@/types/feed/feed-read-service";
import { MemberProfileImage } from "@/components/profile/atoms/MemberProfileImage";
import { FeedTitle } from "../atoms/FeedTitle";
import { FeedMoreOption } from "./FeedMoreOption";

interface FeedHeaderProps
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

export function FeedHeader({
  feedUuid,
  memberUuid,
  createdAt,
  updatedAt,
  title,
  categoryName,
  link,
}: FeedHeaderProps) {
  const { data: member, status } = useMemberCompactProfile({ memberUuid });
  if (status !== "success") return null;
  const profileImageUrl = member.profileImageUrl;
  const nickname = member.nickname || memberUuid;

  return (
    <header className="flex flex-col mb-[0.25rem]">
      <div className="flex justify-between items-center">
        <div className="flex items-center mb-[0.25rem]">
          <div className="mr-[1rem]">
            <MemberProfileImage
              {...{ size: "2rem", profileImageUrl, nickname }}
              link
            />
          </div>
          <div className="flex flex-col">
            <Link href={`/profile/${nickname}`} className="mr-[1rem]">
              {nickname}
            </Link>
            <PostedAt {...{ createdAt, updatedAt }} />
          </div>
        </div>
        <div>
          <FeedMoreOption {...{ feedUuid, memberUuid }} />
        </div>
      </div>
      <FeedTitle {...{ feedUuid, title, categoryName, link }} />
    </header>
  );
}

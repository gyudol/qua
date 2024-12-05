"use client";

import Image from "next/image";
import { PostedAt } from "@/components/common/atoms";
import { useMemberCompactProfile } from "@/hooks";
import type { Feed } from "@/types/feed/feed-read-service";
import { FeedMoreOption } from "./FeedMoreOption";

type FeedHeaderProps = Pick<
  Feed,
  "feedUuid" | "memberUuid" | "createdAt" | "updatedAt" | "title"
>;

export function FeedHeader({
  feedUuid,
  memberUuid,
  createdAt,
  updatedAt,
  title,
}: FeedHeaderProps) {
  const { data: member } = useMemberCompactProfile({ memberUuid });
  const defaultProfileImage = "/dummies/members/member-001.png";
  const profileImage = member?.profileImageUrl || defaultProfileImage;
  const nickname = member?.nickname || memberUuid;

  return (
    <header className="flex flex-col mb-[0.25rem]">
      <div className="flex justify-between items-center">
        <div className="flex items-center mr-[1rem]">
          <div className="mr-[1rem]">
            <figure className="relative w-[3rem] h-[3rem] rounded-full">
              <Image src={profileImage} alt={nickname} fill />
            </figure>
          </div>
          <div className="flex flex-col">
            <div>{nickname}</div>
            <PostedAt {...{ createdAt, updatedAt }} />
          </div>
        </div>
        <div>
          <FeedMoreOption {...{ feedUuid, memberUuid }} />
        </div>
      </div>
      <div className="flex-1">
        <h2 className="text-lg font-bold">{title}</h2>
      </div>
    </header>
  );
}

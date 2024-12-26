"use client";

import Link from "next/link";
import { Fish } from "lucide-react";
import { PostedAt, Skeleton } from "@/components/common/atoms";
import { useMemberCompactProfile } from "@/hooks";
import type { Feed } from "@/types/feed/feed-read-service";
import { MemberProfileImage } from "@/components/profile/atoms/MemberProfileImage";
import { FeedMoreOption } from "./FeedMoreOption";

interface FeedHeaderProps
  extends Pick<Feed, "feedUuid" | "memberUuid" | "createdAt" | "updatedAt"> {
  link?: boolean;
}

export function FeedHeader({
  feedUuid,
  memberUuid,
  createdAt,
  updatedAt,
}: FeedHeaderProps) {
  const { data: member, status } = useMemberCompactProfile({ memberUuid });

  let profileImageUrl, nickname;

  if (status === "error") {
    profileImageUrl = undefined;
    nickname = "알 수 없는 사용자";
  } else {
    profileImageUrl = member?.profileImageUrl;
    nickname = member?.nickname;
  }

  return (
    <header className="flex flex-col mb-[0.25rem] h-[2.4rem]">
      <div className="flex justify-between items-center">
        <div className="flex items-center mb-[0.25rem]">
          <div className="mr-[1rem]">
            {profileImageUrl && nickname ? (
              <MemberProfileImage
                {...{ size: "2.4rem", profileImageUrl, nickname }}
                link
              />
            ) : (
              <div className="size-[2.4rem] rounded-full bg-teal-400 flex justify-center items-center">
                <Fish className="fill-white" />
              </div>
            )}
          </div>
          <div className="flex flex-col">
            {nickname ? (
              <Link
                href={`/profile/${nickname}`}
                className="mr-[1rem] text-sm font-bold text-slate-600"
              >
                {nickname}
              </Link>
            ) : (
              <Skeleton className="mr-[1rem] h-[1.25rem] w-[6rem] rounded-lg" />
            )}

            <PostedAt {...{ createdAt, updatedAt }} />
          </div>
        </div>
        <div>
          <FeedMoreOption
            {...{
              feedUuid,
              memberUuid,
              nickname: status === "success" ? nickname : undefined,
            }}
          />
        </div>
      </div>
    </header>
  );
}

"use client";
import type { Member } from "@/types/member";
import type { BaseFeed, CUAt } from "@/types/contents";
import { PostedAt } from "@/components/common/atoms";
import Profile from "@/components/profile/molecules/Profile";
import { getProfileUrl } from "@/functions/utils";
import { FeedButton } from "./FeedButtonGroup";

export default function FeedHeader({
  nickname,
  profileImageUrl,
  feedUuid,
  createdAt,
  updatedAt,
}: Member & BaseFeed & CUAt) {
  const profileUrl = getProfileUrl(nickname);
  return (
    <div className="flex justify-between items-center">
      <div className="flex gap-[12px]">
        <Profile.PictureWithLink
          href={profileUrl}
          src={profileImageUrl}
          alt={nickname}
        />
        <div className="flex flex-col justify-around">
          <Profile.NameWithLink href={profileUrl} nickname={nickname} />
          <PostedAt postedAt={updatedAt || createdAt} />
        </div>
      </div>
      <FeedButton.Dropdown {...{ feedUuid }} />
    </div>
  );
}

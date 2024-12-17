"use client";

import Link from "next/link";
import { Fish } from "lucide-react";
import type { Shorts } from "@/types/shorts/shorts-read-service";
import { useMemberCompactProfile } from "@/hooks";
import { MemberProfileImage } from "@/components/profile/atoms/MemberProfileImage";
import { ShortsHashtag } from "../atoms/ShortsHashtag";
import { ShortsFollowButton } from "../atoms";

type ShortsPannelProp = Shorts;

export function ShortsPannel({
  memberUuid,
  title,
  hashtags,
}: ShortsPannelProp) {
  const { data, status } = useMemberCompactProfile({ memberUuid });

  let nickname, profileImageUrl;

  if (status === "error") {
    nickname = "알 수 없는 사용자";
    profileImageUrl = undefined;
  } else {
    nickname = data?.nickname || "";
    profileImageUrl = data?.profileImageUrl;
  }

  return (
    <>
      <div className="absolute bottom-0 size-full bg-gradient-to-b from-75% from-transparent to-[rgba(0,0,0,0.5)] pointer-events-none" />
      <div className="absolute max-w-[calc(100%-100px)] bottom-10 left-5 flex flex-col gap-1 text-white">
        <div className="flex items-center mb-[0.25rem]">
          <div className="mr-[1rem]">
            {profileImageUrl ? (
              <MemberProfileImage
                {...{ size: "2rem", profileImageUrl, nickname }}
                link
              />
            ) : (
              <div className="size-[2rem] rounded-full bg-teal-400 flex justify-center items-center">
                <Fish className="fill-white stroke-black" />
              </div>
            )}
          </div>
          <div className="flex flex-col">
            <Link href={`/profile/${nickname}`} className="mr-[1rem]">
              {nickname}
            </Link>
          </div>
          <div>
            {status === "success" ? (
              <ShortsFollowButton {...{ memberUuid }} />
            ) : null}
          </div>
        </div>

        <div className="line-clamp-1">{title}</div>
        <ul className="flex flex-wrap gap-[0.5rem] items-center">
          {(hashtags || []).map((hashtag) => (
            <li key={hashtag.name}>
              <ShortsHashtag {...{ ...hashtag }} />
            </li>
          ))}
        </ul>
      </div>
    </>
  );
}

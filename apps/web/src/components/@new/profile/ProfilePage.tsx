import { Separator } from "@repo/ui/shadcn/separator";
import Link from "next/link";
import Image from "next/image";
import { Info } from "lucide-react";
import { getHashtagInterests } from "@/actions/member-service/interests";
import { getBadge, getGrade } from "@/actions/reward-service";
import { formatToNumAbbrs } from "@/functions/utils";
import type { MemberProfile } from "@/types/member/member-read-service";
import { MemberProfileImage } from "@/components/profile/atoms/MemberProfileImage";
import PageContainer from "../layouts/containers/PageContainer";
import ProfileFollowButton from "./ProfileFollowButton";
import ContentsSection from "./ContentsSeciton";
import ProfileLogoutButton from "./ProfileLogoutButton";

function ProfileStat({ count, label }: { count: number; label: string }) {
  return (
    <div className="flex-1 flex flex-col items-center">
      <div className="text-lg font-bold">{formatToNumAbbrs(count)}</div>
      <p className="text-xs text-zinc-400 font-thin">{label}</p>
    </div>
  );
}

export default async function ProfilePage({
  nickname,
  memberUuid,
  profileImageUrl,
  shortsCount,
  feedCount,
  followerCount,
  followingCount,
  equippedBadgeId,
  gradeId,
  my,
}: MemberProfile & { my?: boolean }) {
  const [hashtags, grade, equippedBadge] = await Promise.all([
    getHashtagInterests({ memberUuid }),
    getGrade({ gradeId }),
    equippedBadgeId ? getBadge({ badgeId: equippedBadgeId }) : null,
  ]);

  return (
    <PageContainer>
      <div className="w-full flex flex-col gap-[2rem] px-[1rem] py-[2rem]">
        <div className="w-full flex justify-between items-center gap-[1rem]">
          <MemberProfileImage
            {...{ profileImageUrl, nickname, size: "5rem" }}
          />
          <div className="flex-1 h-[2.5rem] px-[1rem] flex justify-between items-center">
            <ProfileStat
              {...{ count: shortsCount + feedCount, label: "Posts" }}
            />
            <Separator orientation="vertical" />
            <Link
              href={`/profile/${nickname}/followers`}
              className="flex-1 flex"
            >
              <ProfileStat {...{ count: followerCount, label: "Follwers" }} />
            </Link>

            <Separator orientation="vertical" />

            <Link
              href={`/profile/${nickname}/followings`}
              className="flex-1 flex"
            >
              <ProfileStat
                {...{ count: followingCount, label: "Followings" }}
              />
            </Link>
          </div>
        </div>
        <div className="flex flex-col gap-2">
          <div className="flex gap-1 items-center">
            <p className="text-lg">{nickname}</p>
            <figure className="relative size-[1.5rem]">
              <Image
                src={`https://media.qua.world/${grade.imageUrl}`}
                alt={grade.name}
                fill
              />
            </figure>
            {equippedBadge ? (
              <figure className="relative size-[1.5rem]">
                <Image
                  src={`https://media.qua.world/${equippedBadge.imageUrl}`}
                  alt={nickname}
                  fill
                />
              </figure>
            ) : null}
          </div>
          <div className="flex gap-2 flex-wrap">
            {hashtags.map(({ name }) => (
              <Link key={name} href={`/search?keyword=#${name}`}>
                <div className="bg-teal-400 text-white text-xs px-2 py-1 rounded-lg">
                  #{name}
                </div>
              </Link>
            ))}
          </div>
        </div>
        <div className="w-full flex gap-[1rem]">
          {!my ? (
            <ProfileFollowButton {...{ memberUuid }} />
          ) : (
            <ProfileLogoutButton />
          )}
          <Link href={`/profile/${nickname}/info`} className="w-[3rem]">
            <div className="w-full h-[3rem] flex gap-2 justify-center items-center border-2 border-teal-400 rounded-lg">
              <Info className="stroke-teal-400" />
            </div>
          </Link>
        </div>
      </div>
      <Separator className="h-[1rem] bg-zinc-100" />
      <ContentsSection {...{ memberUuid, my }} />
    </PageContainer>
  );
}

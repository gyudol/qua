import { Separator } from "@repo/ui/shadcn/separator";
import Image from "next/image";
import { Info } from "lucide-react";
import Link from "next/link";
import PageContainer from "@/components/@new/layouts/containers/PageContainer";
import { getMemberProfileByNickname } from "@/actions/member-read-service";
import { MemberProfileImage } from "@/components/profile/atoms/MemberProfileImage";
import { formatToNumAbbrs } from "@/functions/utils";
import { getHashtagInterests } from "@/actions/member-service/interests";
import { getBadge, getGrade } from "@/actions/reward-service";
import ContentsSection from "@/components/@new/profile/ContentsSeciton";
import ProfileFollowButton from "@/components/@new/profile/ProfileFollowButton";

function ProfileStat({ count, label }: { count: number; label: string }) {
  return (
    <div className="flex-1 flex flex-col items-center">
      <div className="text-lg font-bold">{formatToNumAbbrs(count)}</div>
      <p className="text-xs text-zinc-400 font-thin">{label}</p>
    </div>
  );
}

export default async function page({
  params: { nickname: _nickname },
}: {
  params: {
    nickname: string;
  };
}) {
  const nickname = decodeURI(_nickname);
  // const session = await getServerSession(options);
  // if (session?.user) {
  //   const { memberUuid } = session.user as { memberUuid: string };
  //   const sessionNickname = await getMemberNickname({ memberUuid });
  //   if (nickname === sessionNickname) redirect("/my");
  // }

  const {
    memberUuid,
    profileImageUrl,
    shortsCount,
    feedCount,
    followerCount,
    followingCount,
    equippedBadgeId,
    gradeId,
  } = await getMemberProfileByNickname({ nickname });

  const [hashtags, equippedBadge, grade] = await Promise.all([
    getHashtagInterests({ memberUuid }),
    getBadge({ badgeId: equippedBadgeId }),
    getGrade({ gradeId }),
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
            <figure className="relative size-[1.5rem]">
              <Image
                src={`https://media.qua.world/${equippedBadge.imageUrl}`}
                alt={nickname}
                fill
              />
            </figure>
          </div>
          <div className="flex gap-2">
            {hashtags.map(({ name }) => (
              <Link key={name} href={`/search?tag=${name}`}>
                <div className="bg-teal-400 text-white text-xs px-2 py-1 rounded-lg">
                  #{name}
                </div>
              </Link>
            ))}
          </div>
        </div>
        <div className="w-full flex gap-[1rem]">
          <ProfileFollowButton {...{ memberUuid }} />
          <Link href="/">
            <div className="size-[3rem] flex justify-center items-center border-2 border-teal-400 rounded-lg">
              <Info className="stroke-teal-400" />
            </div>
          </Link>
        </div>
      </div>
      <Separator className="h-[1rem] bg-zinc-100" />
      <ContentsSection {...{ memberUuid }} />
    </PageContainer>
  );
}

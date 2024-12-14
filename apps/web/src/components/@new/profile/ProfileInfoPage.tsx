import { Separator } from "@repo/ui/shadcn/separator";
import Image from "next/image";
import { ImageIcon, PencilLine } from "lucide-react";
import { cn } from "@repo/ui/lib/utils";
import Link from "next/link";
import { getHashtagInterests } from "@/actions/member-service/interests";
import {
  getAllMemberBadges,
  getBadge,
  getGrade,
  getMemberPoint,
} from "@/actions/reward-service";
import type { MemberProfile } from "@/types/member/member-read-service";
import { MemberProfileImage } from "@/components/profile/atoms/MemberProfileImage";
import PageContainer from "../layouts/containers/PageContainer";

export default async function ProfileInfoPage({
  nickname,
  memberUuid,
  profileImageUrl,
  equippedBadgeId,
  gradeId,
  my,
}: MemberProfile & { my?: boolean }) {
  const [equippedBadge, grade] = await Promise.all([
    equippedBadgeId ? getBadge({ badgeId: equippedBadgeId }) : null,
    getGrade({ gradeId }),
  ]);

  return (
    <PageContainer>
      <section className="px-[1rem] py-[2rem] flex flex-col gap-3 items-center">
        <div className="relative p-1">
          <MemberProfileImage
            {...{ profileImageUrl, nickname, size: "8rem" }}
          />
          <button
            type="button"
            className="absolute -right-1 -bottom-1 size-[2.5rem] flex justify-center items-center bg-teal-400 p-1 rounded-2xl"
          >
            <ImageIcon size="1.5rem" className="stroke-white" />
          </button>
        </div>
        <div className="flex gap-2 items-center">
          <p className="text-lg font-bold">{nickname}</p>
          <div className="flex">
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
        </div>
        {my ? (
          <div>
            <button
              type="button"
              className="flex text-teal-400 px-2 py-1 border-2 border-teal-400 rounded-lg"
            >
              <PencilLine />
              <span>edit</span>
            </button>
          </div>
        ) : null}
      </section>
      <Separator className="h-[0.5rem] bg-zinc-100" />
      <GradeAndPointSection {...{ memberUuid, gradeId }} />
      <Separator className="h-[0.5rem] bg-zinc-100" />
      <BadgeAndInterestsSection {...{ memberUuid, equippedBadgeId, my }} />
    </PageContainer>
  );
}

async function GradeAndPointSection({
  memberUuid,
  gradeId,
}: {
  memberUuid: string;
  gradeId: number;
}) {
  const [grade, { point }] = await Promise.all([
    getGrade({ gradeId }),
    getMemberPoint({ memberUuid }),
  ]);

  return (
    <section className="w-full py-[2rem] px-[3rem] flex justify-center items-center">
      <div className="w-full flex flex-col items-center gap-2">
        <div className="w-full flex gap-[2rem] items-center">
          <div className="flex-1 flex gap-2 items-center">
            <figure className="relative size-[3rem]">
              <Image
                src={`https://media.qua.world/${grade.imageUrl}`}
                alt={grade.name}
                fill
              />
            </figure>
            <span className="text-[1.25rem] font-bold">{grade.name}</span>
          </div>
          <div className="flex-1 text-[2rem] text-teal-400 font-bold text-center">
            {point} pt
          </div>
        </div>
        <div className="text-zinc-400">
          다음 등급까지 {grade.pointThreshold}
        </div>
      </div>
    </section>
  );
}

async function BadgeListItem({
  badgeId,
  equipped,
  my,
}: {
  badgeId: number;
  equipped?: boolean;
  my?: boolean;
}) {
  const badge = await getBadge({ badgeId });

  return (
    <div className="w-full h-[5rem] flex gap-[1rem] items-center">
      <div className="flex-1 flex gap-2 items-center">
        <figure className="relative size-[3rem]">
          <Image
            src={`https://media.qua.world/${badge.imageUrl}`}
            alt={badge.name}
            fill
          />
        </figure>
        <div className="flex-1">
          <div className="flex justify-between items-center">
            <div className="font-bold text-[1.2rem]">{badge.name}</div>
            {equipped || my ? (
              <div
                className={cn(
                  "w-[5rem] flex gap-1 justify-center font-bold rounded-lg",
                  "px-2 py-1",
                  equipped
                    ? "text-teal-400 border border-teal-400"
                    : "text-white bg-teal-400",
                )}
              >
                {equipped ? <span>장착 중</span> : <span>장착하기</span>}
              </div>
            ) : null}
          </div>
          <div className="w-[calc(100%-8rem)] text-zinc-400 line-clamp-1">
            {badge.description}
          </div>
        </div>
      </div>
    </div>
  );
}

async function BadgeAndInterestsSection({
  memberUuid,
  equippedBadgeId,
  my,
}: {
  memberUuid: string;
  equippedBadgeId: number | null;
  my?: boolean;
}) {
  const badges = await getAllMemberBadges({ memberUuid });
  const interests = await getHashtagInterests({ memberUuid });

  return (
    <section className="w-full px-[1rem] py-[2rem]">
      <div className="flex flex-col gap-[1rem]">
        <div className="flex gap-[1rem] items-center">
          <h3 className="font-bold">보유 뱃지 목록</h3>
          {badges.length > 3 ? (
            <div className="text-sm border-b border-black">더보기</div>
          ) : null}
        </div>
        <ul className="w-full flex flex-col">
          {equippedBadgeId ? (
            <li className="w-full">
              <BadgeListItem {...{ badgeId: equippedBadgeId, my }} equipped />
            </li>
          ) : null}
          {badges
            .filter(({ equipped }) => !equipped)
            .slice(0, equippedBadgeId ? 2 : 3)
            .map(({ badgeId }) => (
              <BadgeListItem key={badgeId} {...{ badgeId, my }} />
            ))}
        </ul>
      </div>
      <div className="flex flex-col gap-[1rem]">
        <div className="flex gap-[1rem] items-center">
          <h3 className="font-bold">관심사 관리</h3>
        </div>
        <div className="flex gap-2 flex-wrap">
          {interests.map(({ name }) => (
            <Link key={name} href={`/search?tag=${name}`}>
              <div className="border-2 border-teal-400 text-teal-400 font-bold text px-2 py-1 rounded-2xl">
                #{name}
              </div>
            </Link>
          ))}
        </div>
      </div>
    </section>
  );
}

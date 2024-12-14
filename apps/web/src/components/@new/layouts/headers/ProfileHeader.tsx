import { ChevronLeft } from "lucide-react";
import Image from "next/image";
import { getMemberProfileByNickname } from "@/actions/member-read-service";
import { getBadge, getGrade } from "@/actions/reward-service";
import BackButton from "../../common/BackButton";

export default async function ProfileHeader({
  nickname,
}: {
  nickname: string;
}) {
  const { equippedBadgeId, gradeId } = await getMemberProfileByNickname({
    nickname,
  });
  const [equippedBadge, grade] = await Promise.all([
    equippedBadgeId ? getBadge({ badgeId: equippedBadgeId }) : null,
    getGrade({ gradeId }),
  ]);

  return (
    <>
      <div className="w-full min-h-[4.5rem]" />
      <header
        className="
      absolute top-0  z-30
      w-full  h-[4.5rem]
      flex    flex-row
      px-4    shadow-md
      justify-between items-center
      "
      >
        <BackButton>
          <ChevronLeft className="" />
        </BackButton>
        <h1 className="text-[0rem]">QUA</h1>
        <div className="w-full flex flex-row justify-between">
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
        </div>
      </header>
    </>
  );
}

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
    getBadge({ badgeId: equippedBadgeId }),
    getGrade({ gradeId }),
  ]);

  return (
    <>
      <div className="w-full min-h-[4rem]" />
      <header
        className="
      absolute top-0  z-30
      w-full  h-[4rem] bg
      flex    flex-row
      px-4    
      justify-between items-center
      shadow-md
      "
      >
        <BackButton>
          <ChevronLeft className="stroke-teal-400" />
        </BackButton>
        <h1 className="text-[0rem]">QUA</h1>
        <div className="w-full flex flex-row justify-between">
          <div className="text-teal-400 text-[1.25rem] font-semibold flex gap-4 items-center">
            <div className="flex gap-2 items-center">
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
          </div>
        </div>
      </header>
    </>
  );
}

import { getMemberProfileByUuid } from "@/actions/member-read-service";
import { ProfileBadgeList } from "../molecules/ProfileBadgeList";
import { ProfileInfo } from "../molecules/ProfileInfo";
import { ProfileStat } from "../molecules/ProfileStat";

interface ProfileCardSectionProps {
  nickname: string;
}

export async function ProfileCardSection({
  nickname,
}: ProfileCardSectionProps) {
  const {
    memberUuid,
    profileImageUrl,
    // point,
    // grade,
    // equippedBadge,
    followerCount,
    followingCount,
    feedCount,
    shortsCount,
  } = await getMemberProfileByUuid({
    memberUuid: "a18bc14a-6d97-4e3f-a6f4-65e9fe914146",
  });
  return (
    <section className="w-full flex flex-col">
      <ProfileInfo {...{ memberUuid, nickname, profileImageUrl }} />
      <ProfileStat
        {...{ followerCount, followingCount, feedCount, shortsCount }}
      />
      <ProfileBadgeList />
    </section>
  );
}

import { getMemberProfileByNickname } from "@/actions/member-read-service";
import { ProfileBadgeList } from "../molecules/ProfileBadgeList";
import { ProfileInfo } from "../molecules/ProfileInfo";
import { ProfileStat } from "../molecules/ProfileStat";
import { ProfileContentsSection } from "../templates/ProfileContentsSection";

interface ProfilePageProps {
  nickname: string;
}

export async function ProfilePage({ nickname }: ProfilePageProps) {
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
  } = await getMemberProfileByNickname({ nickname });
  return (
    <>
      <section className="w-full flex flex-col">
        <ProfileInfo {...{ memberUuid, nickname, profileImageUrl }} />
        <ProfileStat
          {...{ followerCount, followingCount, feedCount, shortsCount }}
        />
        <ProfileBadgeList />
      </section>
      <ProfileContentsSection {...{ memberUuid }} />
    </>
  );
}

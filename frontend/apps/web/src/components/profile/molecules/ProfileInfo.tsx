import { CircleCheckBig } from "lucide-react";
import type { MemberProfile } from "@/types/member/member-read-service";
import { ProfileFollowButton } from "../atoms/ProfileFollowButton";
import { MemberProfileImage } from "../atoms/MemberProfileImage";

type ProfileInfoProps = Pick<
  MemberProfile,
  "memberUuid" | "nickname" | "profileImageUrl"
>;

export function ProfileInfo({
  memberUuid,
  nickname,
  profileImageUrl,
}: ProfileInfoProps) {
  return (
    <header className="p-[1rem] flex">
      <div className="mr-[1.75rem]">
        <MemberProfileImage {...{ profileImageUrl, nickname, size: "5rem" }} />
      </div>
      <div className="w-full flex flex-col">
        <div className="h-[1.5rem] mb-[0.75rem] flex">
          <span className="mr-[0.5rem]">{nickname}</span>
          <span>
            <CircleCheckBig />
          </span>
        </div>

        <ProfileFollowButton {...{ memberUuid }} />
      </div>
    </header>
  );
}

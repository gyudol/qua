import { formatToNumAbbrs } from "@/functions/utils";
import type { MemberProfile } from "@/types/member/member-read-service";

type ProfileStatProp = Pick<
  MemberProfile,
  "followerCount" | "followingCount" | "feedCount" | "shortsCount"
>;

export function ProfileStat({
  followerCount,
  followingCount,
  feedCount,
  shortsCount,
}: ProfileStatProp) {
  return (
    <ul className="w-full flex py-[0.75rem]">
      <li className="flex-1 flex flex-col items-center">
        <span>{formatToNumAbbrs(feedCount + shortsCount)}</span>
        <span>posts</span>
      </li>
      <li className="flex-1 flex flex-col items-center">
        <span>{formatToNumAbbrs(followerCount)}</span>
        <span>followers</span>
      </li>
      <li className="flex-1 flex flex-col items-center">
        <span>{formatToNumAbbrs(followingCount)}</span>
        <span>following</span>
      </li>
    </ul>
  );
}

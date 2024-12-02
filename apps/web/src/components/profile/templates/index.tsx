import {
  Circle,
  CircleCheckBig,
  CircleEllipsis,
  PlusCircle,
} from "lucide-react";
import { formatToNumAbbrs } from "@/functions/utils";

function FollowButton() {
  return (
    <button
      type="button"
      className="w-full h-[2rem] bg-[var(--theme-color)] rounded-xl text-white"
    >
      팔로우
    </button>
  );
}

interface ProfileInfoProps {
  nickname: string;
}

function ProfileInfo({ nickname }: ProfileInfoProps) {
  return (
    <header className="p-[1rem] flex">
      <div>
        <figure className="w-[5rem] h-[5rem] mr-[1.75rem] bg-[var(--theme-color)] rounded-full" />
      </div>
      <div className="w-full flex flex-col">
        <div className="h-[1.5rem] mb-[0.75rem] flex">
          <span className="mr-[0.5rem]">{nickname}</span>
          <span>
            <CircleCheckBig />
          </span>
        </div>

        <FollowButton />
      </div>
    </header>
  );
}

function ProfileStat() {
  return (
    <ul className="w-full flex py-[0.75rem]">
      <li className="flex-1 flex flex-col items-center">
        <span>{formatToNumAbbrs(7354)}</span>
        <span>posts</span>
      </li>
      <li className="flex-1 flex flex-col items-center">
        <span>{formatToNumAbbrs(3245757)}</span>
        <span>followers</span>
      </li>
      <li className="flex-1 flex flex-col items-center">
        <span>{formatToNumAbbrs(12344)}</span>
        <span>following</span>
      </li>
    </ul>
  );
}

function Badge() {
  return (
    <Circle
      width="3.5rem"
      height="3.5rem"
      strokeWidth="1px"
      stroke="white"
      fill="#DDD"
      className="rounded-full bg-[#DDD]"
    />
  );
}

function ProfileBadgeList() {
  return (
    <ul className="flex p-[1rem] gap-[0.75rem] flex-wrap">
      <li>
        <PlusCircle
          width="3.5rem"
          height="3.5rem"
          strokeWidth="1px"
          stroke="white"
          fill="var(--theme-color)"
          className="rounded-full bg-[var(--theme-color)]"
        />
      </li>
      <li>
        <Badge />
      </li>
      <li>
        <Badge />
      </li>
      <li>
        <Badge />
      </li>
      <li>
        <CircleEllipsis
          width="3.5rem"
          height="3.5rem"
          strokeWidth="1px"
          stroke="white"
          fill="#DDD"
          className="rounded-full bg-[#DDD]"
        />
      </li>
    </ul>
  );
}

interface ProfileCardSectionProps {
  nickname: string;
}

export function ProfileCardSection({ nickname }: ProfileCardSectionProps) {
  return (
    <section className="w-full flex flex-col">
      <ProfileInfo {...{ nickname }} />
      <ProfileStat />
      <ProfileBadgeList />
    </section>
  );
}

import { Circle, CircleEllipsis, PlusCircle } from "lucide-react";

export function ProfileBadge() {
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

export function ProfileBadgeMore() {
  return (
    <CircleEllipsis
      width="3.5rem"
      height="3.5rem"
      strokeWidth="1px"
      stroke="white"
      fill="#DDD"
      className="rounded-full bg-[#DDD]"
    />
  );
}

export function ProfileBadgeAdd() {
  return (
    <PlusCircle
      width="3.5rem"
      height="3.5rem"
      strokeWidth="1px"
      stroke="white"
      fill="var(--theme-color)"
      className="rounded-full bg-teal-400"
    />
  );
}

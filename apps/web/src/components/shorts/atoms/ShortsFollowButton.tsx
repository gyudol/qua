"use client";

import { useSessionContext } from "@/context/SessionContext";
import {
  useFollowMutation,
  useFollowStatusQuery,
} from "@/hooks/utility-service/follow-service";

interface ShortsFollowButtonProp {
  memberUuid: string;
}

export function ShortsFollowButton({ memberUuid }: ShortsFollowButtonProp) {
  const { isAuthenticated } = useSessionContext();

  const { data: followStatus } = useFollowStatusQuery({
    targetUuid: memberUuid,
  });

  const { mutate } = useFollowMutation({
    targetUuid: memberUuid,
  });

  function handleClick() {
    if (isAuthenticated) mutate(!followStatus);
  }

  return (
    <button
      type="button"
      className={`px-[1rem] py-[0.25rem] rounded-xl text-white ${followStatus ? "bg-slate-400" : "bg-teal-400"}`}
      onClick={handleClick}
    >
      {followStatus ? "언팔로우" : "팔로우"}
    </button>
  );
}

"use client";

import { useSessionContext } from "@/context/SessionContext";
import {
  useFollowMutation,
  useFollowStatusQuery,
} from "@/hooks/utility-service/follow-service";

interface ProfileFollowButtonProp {
  memberUuid: string;
}

export function ProfileFollowButton({ memberUuid }: ProfileFollowButtonProp) {
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
      className={`w-full h-[2rem] rounded-xl text-white ${followStatus ? "bg-slate-400" : "bg-teal-400"}`}
      onClick={handleClick}
    >
      {followStatus ? "언팔로우" : "팔로우"}
    </button>
  );
}

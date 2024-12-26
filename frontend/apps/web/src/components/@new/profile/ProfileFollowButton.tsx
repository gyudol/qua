"use client";

import { Plus } from "lucide-react";
import { cn } from "@repo/ui/lib/utils";
import { useSessionContext } from "@/context/SessionContext";
import {
  useFollowMutation,
  useFollowStatusQuery,
} from "@/hooks/utility-service/follow-service";
import { ButtonWithAuth } from "@/components/common/atoms";

export default function ProfileFollowButton({
  memberUuid,
}: {
  memberUuid: string;
}) {
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
    <ButtonWithAuth
      className={cn(
        "flex-1 flex gap-1 rounded-lg",
        "justify-center items-center",
        followStatus
          ? "bg-white text-teal-400 font-bold border-2 border-teal-400"
          : "bg-teal-400 text-white",
      )}
      onClick={handleClick}
    >
      <span className="flex text-[1rem]">
        {followStatus ? "Following" : "Follow"}
      </span>
      {followStatus ? null : <Plus size="1.5rem" />}
    </ButtonWithAuth>
  );
}

"use client";

import { useSession } from "next-auth/react";

export function useSessionMemberUuid() {
  const { data, status } = useSession();
  const { memberUuid } = (data &&
    (data as { user?: { memberUuid: string } }).user) || {
    memberUuid: "",
  };

  return { memberUuid, status };
}

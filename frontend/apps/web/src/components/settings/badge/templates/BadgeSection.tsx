"use client";

import {
  useGetAllMemberBadges,
  usePutMemberBadgeStatusMutation,
} from "@/hooks/reward-service";
import type { MemberProfile } from "@/types/member/member-read-service";
import { MemberBadgeListItem } from "../organisms/MemberBadgeListItem";

type MemberBadgeListSectionProps = Pick<MemberProfile, "memberUuid">;

export function MemberBadgeListSection({
  memberUuid,
}: MemberBadgeListSectionProps) {
  const { data: badges, status, error } = useGetAllMemberBadges({ memberUuid });
  const { mutate } = usePutMemberBadgeStatusMutation({ memberUuid });

  if (status === "error") throw Error(error.message);
  if (status === "pending") return;

  return (
    <section>
      <div>
        {badges.map((badgeStatus) => (
          <MemberBadgeListItem
            key={badgeStatus.badgeId}
            {...{ ...badgeStatus, mutate }}
          />
        ))}
      </div>
    </section>
  );
}

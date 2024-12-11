import Image from "next/image";
import type { UseMutateFunction } from "@tanstack/react-query";
import { useGetBadge } from "@/hooks/reward-service";
import type {
  BadgeStatus,
  PutMemberBadgeStatusReq,
} from "@/types/reward-service";

interface MemberBadgeListItemProps extends BadgeStatus {
  mutate: UseMutateFunction<
    void,
    Error,
    Omit<PutMemberBadgeStatusReq, "memberUuid">
  >;
}

export function MemberBadgeListItem({
  badgeId,
  equipped,
  mutate,
}: MemberBadgeListItemProps) {
  const { data: badge, status, error } = useGetBadge({ badgeId });
  if (status === "error") throw Error(error.message);
  if (status === "pending") return;

  function handleClick() {
    mutate({ badgeId, equipped: !equipped });
  }

  return (
    <div>
      <figure
        className={`relative size-[5rem] border-2 ${equipped ? "border-blue" : "border-white"}`}
      >
        <Image
          src={`https://media.qua.world/${badge.imageUrl}`}
          alt={badge.name}
          fill
        />
      </figure>
      <div>{badge.name}</div>
      <div>{badge.description}</div>
      <button type="button" onClick={handleClick}>
        {equipped ? "장착 해제" : "장착"}
      </button>
    </div>
  );
}

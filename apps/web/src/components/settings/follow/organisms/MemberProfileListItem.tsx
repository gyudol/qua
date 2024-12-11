import Image from "next/image";
import { useMemberCompactProfile } from "@/hooks";
import type { GetMemberCompactProfileReq } from "@/types/member/member-read-service";

type MemberProfileListItemProps = GetMemberCompactProfileReq;

export function MemberProfileListItem({
  memberUuid,
}: MemberProfileListItemProps) {
  const { data, status, error } = useMemberCompactProfile({ memberUuid });

  if (status === "error") throw Error(error.message);
  if (status === "pending") return;

  return (
    <div>
      <figure className="relative size-[5rem]">
        <Image
          src={`https://media.qua.world/${data.profileImageUrl}`}
          alt={data.nickname}
          fill
        />
      </figure>
      <div>{data.nickname}</div>
    </div>
  );
}

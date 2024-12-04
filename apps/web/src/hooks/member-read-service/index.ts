import { useQuery } from "@tanstack/react-query";
import {
  getMemberCompactProfile,
  getMemberProfileByNickname,
  getMemberProfileByUuid,
} from "@/actions/member-read-service";
import type { GetMemberCompactProfileReq } from "@/types/member/member-read-service";

export function useMemberProfile({
  memberUuid,
  nickname,
}: {
  memberUuid?: string;
  nickname?: string;
}) {
  if (!memberUuid && !nickname) throw Error();

  return useQuery({
    queryKey: [
      "member-service",
      {
        type: "profile",
        memberUuid,
        nickname,
      },
    ],
    queryFn: () => {
      if (memberUuid) {
        return getMemberProfileByUuid({ memberUuid });
      } else if (nickname) {
        return getMemberProfileByNickname({ nickname });
      }
      throw Error("memberUuid or nickanme is needed");
    },
  });
}

export function useMemberCompactProfile({
  memberUuid,
}: GetMemberCompactProfileReq) {
  return useQuery({
    queryKey: ["member-service", { type: "compact-profile", memberUuid }],
    queryFn: () => getMemberCompactProfile({ memberUuid }),
  });
}

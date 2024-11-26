"use server";

import type { MemberProfile } from "@/types/member";
import { getHeaders, processResponse } from "../common";
import { getMemberNickname } from "../member-service";

const API_SERVER = process.env.BASE_API_URL;
const PREFIX = "member-read-service";

export async function getMemberProfile({
  memberUuid,
  nickname,
}: {
  memberUuid?: string;
  nickname?: string;
}) {
  if ((!nickname && !memberUuid) || (nickname && memberUuid))
    throw Error("please input memberUuid or nickname");

  if (!nickname && memberUuid) {
    // eslint-disable-next-line no-param-reassign -- in this case, resassign is needed
    nickname = await getMemberNickname({ memberUuid });
  }

  const URI = `${API_SERVER}/${PREFIX}/v1/members/${nickname}/profile`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  return processResponse<MemberProfile, false>({ res });
}

"use server";

import type { EmptyObject } from "@/types/common";
import { getHeaders, processResponse } from "../common";

const API_SERVER = process.env.BASE_API_URL;
const PREFIX = "member-service";

export async function getMemberNickname({
  memberUuid,
}: {
  memberUuid: string;
}) {
  const URI = `${API_SERVER}/${PREFIX}/v1/members/${memberUuid}/nickname`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  return processResponse<string, false>({ res });
}

export async function putMemberNickname({
  memberUuid,
  nickname,
}: {
  memberUuid: string;
  nickname: string;
}) {
  const URI = `${API_SERVER}/${PREFIX}/v1/members/${memberUuid}/nickname`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "PUT",
    cache: "no-cache",
    body: JSON.stringify({ nickname }),
  });

  return processResponse<string, false>({ res });
}

export async function putMemberProfileImage({
  memberUuid,
  profileImgUrl,
}: {
  memberUuid: string;
  profileImgUrl: string;
}) {
  const URI = `${API_SERVER}/${PREFIX}/v1/members/${memberUuid}/profile-img`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "PUT",
    cache: "no-cache",
    body: JSON.stringify({ profileImgUrl }),
  });

  return processResponse<EmptyObject, false>({ res });
}

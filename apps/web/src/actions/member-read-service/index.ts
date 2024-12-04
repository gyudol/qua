"use server";

import type {
  GetMemberCompactProfileReq,
  GetMemberProfileByNicknameReq,
  GetMemberProfileByUuidReq,
  MemberProfile,
} from "@/types/member/member-read-service";
import { getHeaders, processResponse } from "../common";

const API_SERVER = process.env.BASE_API_URL;
const PREFIX = "member-read-service";

export async function getMemberProfileByNickname({
  nickname,
}: GetMemberProfileByNicknameReq) {
  const URI = `${API_SERVER}/${PREFIX}/v1/members/${nickname}/profile`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  return processResponse<MemberProfile, false>({ res });
}

export async function getMemberProfileByUuid({
  memberUuid,
}: GetMemberProfileByUuidReq) {
  const URI = `${API_SERVER}/${PREFIX}/v1/members/uuid/${memberUuid}/profile`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  return processResponse<MemberProfile, false>({ res });
}

export async function getMemberCompactProfile({
  memberUuid,
}: GetMemberCompactProfileReq) {
  const URI = `${API_SERVER}/${PREFIX}/v1/members/${memberUuid}/compact-profile`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  return processResponse<MemberProfile, false>({ res });
}

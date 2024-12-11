"use server";

import type {
  DeleteFollowingReq,
  FollowStatus,
  GetFollowersReq,
  GetFollowingsReq,
  GetFollowStatusReq,
  PostFollowingReq,
} from "@/types/utility-service";
import type { EmptyObject } from "@/types/common";
import type { MemberUuid } from "@/types/member/common";
import { toURLSearchParams } from "@/functions/utils";
import { getHeaders, getSessionMemberUuid, processResponse } from "../common";

const API_SERVER = process.env.BASE_API_URL;
const PREFIX = "utility-service";

export async function postFollowing({
  sourceUuid,
  targetUuid,
}: PostFollowingReq) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/members/${sourceUuid}/following/${targetUuid}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "POST",
    cache: "no-cache",
  });

  return processResponse<EmptyObject, false>({ res });
}

export async function deleteFollowing({
  sourceUuid,
  targetUuid,
}: DeleteFollowingReq) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/members/${sourceUuid}/following/${targetUuid}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "DELETE",
    cache: "no-cache",
  });

  return processResponse<EmptyObject, false>({ res });
}

export async function getFollowStatus({
  sourceUuid,
  targetUuid,
}: GetFollowStatusReq) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/members/${sourceUuid}/follow-status/${targetUuid}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  return processResponse<FollowStatus, false>({ res });
}

export async function getFollowings({
  memberUuid,
  ...query
}: GetFollowingsReq) {
  const URI = `${API_SERVER}/${PREFIX}/v1/members/${memberUuid || (await getSessionMemberUuid())}/followings?${toURLSearchParams(query)}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  return processResponse<MemberUuid, true>({ res });
}

export async function getFollowers({ memberUuid }: GetFollowersReq) {
  const URI = `${API_SERVER}/${PREFIX}/v1/members/${memberUuid}/followers`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  return processResponse<MemberUuid, true>({ res });
}

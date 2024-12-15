"use server";

import type {
  Badge,
  BadgeStatus,
  GetAllMemberBadgesReq,
  GetBadgeReq,
  GetMemberBadgeStatusReq,
  PostMemberBadgeReq,
  PutMemberBadgeStatusReq,
} from "@/types/reward-service";
import type { EmptyObject } from "@/types/common";
import { getHeaders, processResponse } from "../common";

const API_SERVER = process.env.BASE_API_URL;
const PREFIX = "reward-service";

export async function putMemberBadgeStatus({
  memberUuid,
  badgeId,
  equipped,
}: PutMemberBadgeStatusReq) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/members/${memberUuid}/badges/${badgeId}/equip`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "PUT",
    cache: "no-cache",
    body: JSON.stringify({ equipped }),
  });

  return processResponse<EmptyObject, false>({ res });
}

export async function getAllMemberBadges({
  memberUuid,
}: GetAllMemberBadgesReq) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/members/${memberUuid}/badges`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "force-cache",
  });

  return processResponse<BadgeStatus[], false>({ res });
}

export async function postMemberBadge({
  memberUuid,
  badgeId,
}: PostMemberBadgeReq) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/members/${memberUuid}/badges/${badgeId}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "POST",
    cache: "no-cache",
  });

  return processResponse<EmptyObject, false>({ res });
}

export async function getMemberBadgeStatus({
  memberUuid,
  badgeId,
}: GetMemberBadgeStatusReq) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/members/${memberUuid}/badges/${badgeId}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "force-cache",
  });

  return processResponse<BadgeStatus, false>({ res });
}

export async function getAllBadges() {
  const URI = `${API_SERVER}/${PREFIX}/v1/badge`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "force-cache",
  });

  return processResponse<Badge[], false>({ res });
}

export async function getBadge({ badgeId }: GetBadgeReq) {
  const URI = `${API_SERVER}/${PREFIX}/v1/badge/${badgeId}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "force-cache",
  });

  return processResponse<Badge, false>({ res });
}

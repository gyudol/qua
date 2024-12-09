"use server";

import type {
  GetMemberShortsesReq,
  GetMemberShortsRecsReq,
  GetShortsRecsReq,
  GetShortsReq,
  Shorts,
} from "@/types/shorts/shorts-read-service";
import { toURLSearchParams } from "@/functions/utils";
import { getHeaders, processResponse } from "../common";

const API_SERVER = process.env.BASE_API_URL;
const PREFIX = "shorts-read-service";

export async function getShorts({ shortsUuid }: GetShortsReq) {
  const URI = `${API_SERVER}/${PREFIX}/v1/shorts/${shortsUuid}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  return processResponse<Shorts, false>({ res });
}

export async function getShortsRecs({ ...query }: GetShortsRecsReq) {
  const URI = `${API_SERVER}/${PREFIX}/v1/shorts/recommendation?${toURLSearchParams(query)}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  return processResponse<Shorts, true>({ res });
}

export async function getMemberShortses({
  memberUuid,
  ...query
}: GetMemberShortsesReq) {
  const URI = `${API_SERVER}/${PREFIX}/v1/members/${memberUuid}/shorts?${toURLSearchParams(query)}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  return processResponse<Shorts, true>({ res });
}

export async function getMemberShortsRecs({
  memberUuid,
  ...query
}: GetMemberShortsRecsReq) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/members/${memberUuid}/shorts/recommendation?${toURLSearchParams(query)}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  return processResponse<Shorts, true>({ res });
}

"use server";

import type {
  GetMemberPointHistoryReq,
  GetMemberPointReq,
  MemberPoint,
  PointHistoryRecord,
} from "@/types/reward-service";
import { toURLSearchParams } from "@/functions/utils";
import { getHeaders, processResponse } from "../common";

const API_SERVER = process.env.BASE_API_URL;
const PREFIX = "reward-service";

export async function getMemberPoint({ memberUuid }: GetMemberPointReq) {
  const URI = `${API_SERVER}/${PREFIX}/v1/member/${memberUuid}/point`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "force-cache",
  });

  return processResponse<MemberPoint, false>({ res });
}

export async function getMemberPointHistory({
  memberUuid,
  ...query
}: GetMemberPointHistoryReq) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/member/${memberUuid}/point-history?${toURLSearchParams(query)}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "force-cache",
  });

  return processResponse<PointHistoryRecord, true>({ res });
}

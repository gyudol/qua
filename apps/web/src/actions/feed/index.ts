"use server";

import type { CommonRes } from "@/types/common";
import type { CreateFeedType } from "@/types/request/requestType";
import { getHeaders, getSessionMemberUuid } from "../common";

const BASE_API_URL = process.env.BASE_API_URL;
const PREFIX = "feed-service";

export async function createFeed(payload: CreateFeedType): Promise<boolean> {
  const memberUuid = await getSessionMemberUuid();
  payload.memberUuid = memberUuid;

  const URI = `${BASE_API_URL}/${PREFIX}/auth/v1/feeds`;
  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "POST",
    body: JSON.stringify(payload),
  });

  const { isSuccess } = (await res.json()) as CommonRes<null>;

  return isSuccess;
}

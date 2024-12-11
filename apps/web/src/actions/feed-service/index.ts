"use server";

import type { CommonRes, EmptyObject } from "@/types/common";
import type { CreateFeedType } from "@/types/request/requestType";
import type { FeedReq } from "@/types/feed/common";
import { getHeaders, getSessionMemberUuid, processResponse } from "../common";

const API_SERVER = process.env.BASE_API_URL;
const PREFIX = "feed-service";

export async function createFeed(payload: CreateFeedType): Promise<boolean> {
  const memberUuid = await getSessionMemberUuid();
  payload.memberUuid = memberUuid;

  const URI = `${API_SERVER}/${PREFIX}/auth/v1/feeds`;
  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "POST",
    body: JSON.stringify(payload),
  });

  const { isSuccess } = (await res.json()) as CommonRes<null>;

  return isSuccess;
}

export async function deleteFeed({ feedUuid }: FeedReq) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/feeds/${feedUuid}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "DELETE",
    cache: "no-cache",
  });

  return processResponse<EmptyObject, false>({ res });
}

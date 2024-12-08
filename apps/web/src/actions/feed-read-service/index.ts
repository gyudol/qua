"use server";

import { toURLSearchParams } from "@/functions/utils";
import type {
  Feed,
  GetFeedsReq,
  GetMemberFeeds,
} from "@/types/feed/feed-read-service";
import type { FeedReq } from "@/types/feed/common";
import { getHeaders, processResponse } from "../common";

const API_SERVER = process.env.BASE_API_URL;
const PREFIX = "feed-read-service";

export async function getFeeds({ ...query }: GetFeedsReq) {
  const URI = `${API_SERVER}/${PREFIX}/v1/feeds?${toURLSearchParams(query)}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  return processResponse<Feed, true>({ res });
}

export async function getFeed({ feedUuid }: FeedReq) {
  const URI = `${API_SERVER}/${PREFIX}/v1/feeds/${feedUuid}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  return processResponse<Feed, false>({ res });
}

export async function getMemberFeeds({ memberUuid, ...query }: GetMemberFeeds) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/members/${memberUuid}/feeds?${toURLSearchParams(query)}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  return processResponse<Feed, true>({ res });
}

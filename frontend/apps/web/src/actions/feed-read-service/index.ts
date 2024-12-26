"use server";

import { getServerSession } from "next-auth";
import { toURLSearchParams } from "@/functions/utils";
import type {
  Feed,
  GetFeedRecommentdationsReq,
  GetFeedsReq,
  GetMemberFeeds,
  GetRandomHashtags,
  SearchFeedsReq,
} from "@/types/feed/feed-read-service";
import type { FeedReq } from "@/types/feed/common";
import { options } from "@/app/api/auth/[...nextauth]/authOption";
import type { Hashtag } from "@/types/contents";
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
  const session = await getServerSession(options);

  const URI = session?.user
    ? `${API_SERVER}/${PREFIX}/auth/v1/members/${memberUuid}/feeds?${toURLSearchParams(query)}`
    : `${API_SERVER}/${PREFIX}/v1/members/${memberUuid}/feeds?${toURLSearchParams(query)}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  return processResponse<Feed, true>({ res });
}

export async function getRandomHashtags({ ...query }: GetRandomHashtags) {
  const URI = `${API_SERVER}/${PREFIX}/v1/hashtags?${toURLSearchParams(query)}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  return processResponse<Hashtag[], false>({ res });
}

export async function searchFeeds({ keyword, ...query }: SearchFeedsReq) {
  const URI = `${API_SERVER}/${PREFIX}/v1/search/${keyword}/feeds?${toURLSearchParams(query)}`;
  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  return processResponse<Feed, true>({ res });
}

export async function getFeedRecommendations({
  memberUuid,
  ...query
}: GetFeedRecommentdationsReq) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/members/${memberUuid}/recommend/feeds?${toURLSearchParams(query)}`;
  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  return processResponse<Feed, true>({ res });
}

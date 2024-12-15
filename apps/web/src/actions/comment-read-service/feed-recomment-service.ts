"use server";

import { toURLSearchParams } from "@/functions/utils";
import type {
  FeedRecomment,
  GetFeedRecommentsReq,
} from "@/types/comment/comment-read-service";
import { getHeaders, processResponse } from "../common";

const API_SERVER = process.env.BASE_API_URL;
const PREFIX = "comment-read-service";

export async function getFeedRecomments({
  commentUuid,
  ...query
}: GetFeedRecommentsReq) {
  const URI = `${API_SERVER}/${PREFIX}/v1/feeds/comments/${commentUuid}/recomments?${toURLSearchParams(query)}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "force-cache",
    next: { revalidate: 1 },
  });

  return processResponse<FeedRecomment, true>({ res });
}

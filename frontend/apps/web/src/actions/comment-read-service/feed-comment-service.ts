"use server";

import { toURLSearchParams } from "@/functions/utils";
import type {
  FeedComment,
  GetFeedCommentsReq,
} from "@/types/comment/comment-read-service";
import { getHeaders, processResponse } from "../common";

const API_SERVER = process.env.BASE_API_URL;
const PREFIX = "comment-read-service";

export async function getFeedComments({
  feedUuid,
  ...query
}: GetFeedCommentsReq) {
  const URI = `${API_SERVER}/${PREFIX}/v1/feeds/${feedUuid}/comments?${toURLSearchParams(query)}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  return processResponse<FeedComment, true>({ res });
}

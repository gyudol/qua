"use server";

import { toURLSearchParams } from "@/functions/utils";
import type {
  GetShortsCommentsReq,
  ShortsComment,
} from "@/types/comment/comment-read-service";
import { getHeaders, processResponse } from "../common";

const API_SERVER = process.env.BASE_API_URL;
const PREFIX = "comment-read-service";

export async function getShortsComments({
  shortsUuid,
  ...query
}: GetShortsCommentsReq) {
  const URI = `${API_SERVER}/${PREFIX}/v1/shorts/${shortsUuid}/comments?${toURLSearchParams(query)}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  return processResponse<ShortsComment, true>({ res });
}

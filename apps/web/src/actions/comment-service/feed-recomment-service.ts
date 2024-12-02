"use server";

import type {
  DeleteFeedRecommentReq,
  GetFeedRecommentReq,
  PostFeedRecommentReq,
  PutFeedRecommentReq,
  FeedRecomment,
} from "@/types/comment/comment-service";
import type { EmptyObject } from "@/types/common";
import { getHeaders, processResponse } from "../common";

const API_SERVER = process.env.BASE_API_URL;
const PREFIX = "comment-service";

export async function putFeedRecomment({
  recommentUuid,
  content,
}: PutFeedRecommentReq) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/feeds/comments/recomments/${recommentUuid}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "PUT",
    cache: "no-cache",
    body: JSON.stringify({ content }),
  });

  return processResponse<EmptyObject, false>({ res });
}

export async function deleteFeedRecomment({
  recommentUuid,
}: DeleteFeedRecommentReq) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/feeds/comments/recomments/${recommentUuid}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "DELETE",
    cache: "no-cache",
  });

  return processResponse<EmptyObject, false>({ res });
}

export async function postFeedRecomment({
  commentUuid,
  ...body
}: PostFeedRecommentReq) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/feeds/comments/${commentUuid}/recomments`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "POST",
    cache: "no-cache",
    body: JSON.stringify(body),
  });

  return processResponse<FeedRecomment, false>({ res });
}

export async function getFeedRecomment({ recommentUuid }: GetFeedRecommentReq) {
  const URI = `${API_SERVER}/${PREFIX}/v1/feeds/comments/recomments/${recommentUuid}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  return processResponse<FeedRecomment, false>({ res });
}

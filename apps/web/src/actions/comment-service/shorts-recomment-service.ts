"use server";

import type {
  DeleteShortsRecommentReq,
  GetShortsRecommentReq,
  GetShortsRecommentsReq,
  PostShortsRecommentReq,
  PutShortsRecommentReq,
  ShortsRecomment,
} from "@/types/comment/comment-service";
import type { EmptyObject } from "@/types/common";
import { toURLSearchParams } from "@/functions/utils";
import { getHeaders, processResponse } from "../common";

const API_SERVER = process.env.BASE_API_URL;
const PREFIX = "comment-service";

export async function putShortsRecomment({
  recommentUuid,
  content,
}: PutShortsRecommentReq) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/shorts/comments/recomments/${recommentUuid}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "PUT",
    cache: "no-cache",
    body: JSON.stringify({ content }),
  });

  return processResponse<EmptyObject, false>({ res });
}

export async function deleteShortsRecomment({
  recommentUuid,
}: DeleteShortsRecommentReq) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/shorts/comments/recomments/${recommentUuid}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "DELETE",
    cache: "no-cache",
  });

  return processResponse<EmptyObject, false>({ res });
}

export async function postShortsRecomment({
  commentUuid,
  ...body
}: PostShortsRecommentReq) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/shorts/comments/${commentUuid}/recomments`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "POST",
    cache: "no-cache",
    body: JSON.stringify(body),
  });

  return processResponse<ShortsRecomment, false>({ res });
}

export async function getShortsRecomments({
  commentUuid,
  ...query
}: GetShortsRecommentsReq) {
  const URI = `${API_SERVER}/${PREFIX}/v1/shorts/comments/${commentUuid}/recomments?${toURLSearchParams(query)}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  return processResponse<ShortsRecomment, true>({ res });
}

export async function getShortsRecomment({
  recommentUuid,
}: GetShortsRecommentReq) {
  const URI = `${API_SERVER}/${PREFIX}/v1/shorts/comments/recomments/${recommentUuid}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  return processResponse<ShortsRecomment, false>({ res });
}

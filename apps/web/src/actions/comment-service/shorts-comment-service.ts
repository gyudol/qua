"use server";

import type {
  GetShortsCommentReq,
  PostShortsCommentReq,
  PutShortsCommentReq,
  ShortsComment,
} from "@/types/comment/comment-service";
import type { EmptyObject } from "@/types/common";
import type { DeleteCommentReq } from "@/types/comment/common";
import { getHeaders, processResponse } from "../common";

const API_SERVER = process.env.BASE_API_URL;
const PREFIX = "comment-service";

export async function putShortsComment({
  commentUuid,
  content,
}: PutShortsCommentReq) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/shorts/comments/${commentUuid}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "PUT",
    cache: "no-cache",
    body: JSON.stringify({ content }),
  });

  return processResponse<EmptyObject, false>({ res });
}

export async function deleteShortsComment({ commentUuid }: DeleteCommentReq) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/shorts/comments/${commentUuid}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "DELETE",
    cache: "no-cache",
  });

  return processResponse<EmptyObject, false>({ res });
}

export async function postShortsComment({
  shortsUuid,
  ...body
}: PostShortsCommentReq) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/shorts/${shortsUuid}/comments`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "POST",
    cache: "no-cache",
    body: JSON.stringify(body),
  });

  return processResponse<ShortsComment, false>({ res });
}

export async function getShortsComment({ commentUuid }: GetShortsCommentReq) {
  const URI = `${API_SERVER}/${PREFIX}/v1/shorts/comments/${commentUuid}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  return processResponse<ShortsComment, false>({ res });
}

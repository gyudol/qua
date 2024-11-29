"use server";

import type {
  GetFeedCommentReq,
  GetFeedCommentsReq,
  PostFeedCommentReq,
  PutFeedCommentReq,
  FeedComment,
} from "@/types/comment/comment-service";
import type { EmptyObject } from "@/types/common";
import type { DeleteCommentReq } from "@/types/comment/common";
import { toURLSearchParams } from "@/functions/utils";
import { getHeaders, processResponse } from "../common";

const API_SERVER = process.env.BASE_API_URL;
const PREFIX = "comment-service";

export async function putFeedComment({
  commentUuid,
  content,
}: PutFeedCommentReq) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/feeds/comments/${commentUuid}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "PUT",
    cache: "no-cache",
    body: JSON.stringify({ content }),
  });

  return processResponse<EmptyObject, false>({ res });
}

export async function deleteFeedComment({ commentUuid }: DeleteCommentReq) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/feeds/comments/${commentUuid}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "DELETE",
    cache: "no-cache",
  });

  return processResponse<EmptyObject, false>({ res });
}

export async function postFeedComment({
  feedUuid,
  ...body
}: PostFeedCommentReq) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/feeds/${feedUuid}/comments`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "POST",
    cache: "no-cache",
    body: JSON.stringify(body),
  });

  return processResponse<FeedComment, false>({ res });
}

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

export async function getFeedComment({ commentUuid }: GetFeedCommentReq) {
  const URI = `${API_SERVER}/${PREFIX}/v1/feeds/comments/${commentUuid}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  return processResponse<FeedComment, false>({ res });
}

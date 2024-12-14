"use server";

import type {
  DeleteShortsReq,
  PostShortsReq,
  PutShortsHashtagsReq,
  PutShortsReq,
  PutShortsStatusReq,
} from "@/types/shorts/shorts-service";
import type { EmptyObject } from "@/types/common";
import { getHeaders, processResponse } from "../common";

const API_SERVER = process.env.BASE_API_URL;
const PREFIX = "shorts-service";

export async function PutShorts({ shortsUuid, ...body }: PutShortsReq) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/shorts/${shortsUuid}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "PUT",
    cache: "no-cache",
    body: JSON.stringify(body),
  });

  return processResponse<EmptyObject, false>({ res });
}

export async function deleteShorts({ shortsUuid }: DeleteShortsReq) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/shorts/${shortsUuid}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "DELETE",
    cache: "no-cache",
  });

  return processResponse<EmptyObject, false>({ res });
}

export async function putShortsStatus({
  shortsUuid,
  visibility,
}: PutShortsStatusReq) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/shorts/${shortsUuid}/visibility`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "PUT",
    cache: "no-cache",
    body: JSON.stringify({ visibility }),
  });

  return processResponse<EmptyObject, false>({ res });
}

export async function putShortsHashtags({
  shortsUuid,
  hashtags,
}: PutShortsHashtagsReq) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/shorts/${shortsUuid}/hashtags`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "PUT",
    cache: "no-cache",
    body: JSON.stringify({ hashtags }),
  });

  return processResponse<EmptyObject, false>({ res });
}

export async function postShorts({ ...body }: PostShortsReq) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/shorts`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "POST",
    cache: "no-cache",
    body: JSON.stringify(body),
  });

  return processResponse<EmptyObject, false>({ res });
}

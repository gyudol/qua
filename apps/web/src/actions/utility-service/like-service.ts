import type {
  DislikeStatus,
  GetDislikeStatusReq,
  GetLikesReq,
  GetLikeStatusReq,
  LikeStatus,
  PostDislikeReq,
  PostLikeReq,
} from "@/types/utility-service";
import type { EmptyObject } from "@/types/common";
import { toURLSearchParams } from "@/functions/utils";
import { getHeaders, processResponse } from "../common";

const API_SERVER = process.env.BASE_API_URL;
const PREFIX = "utility-service";

export async function postLike({ ...body }: PostLikeReq) {
  const URI = `${API_SERVER}/${PREFIX}/v1/members/likes`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "POST",
    cache: "no-cache",
    body: JSON.stringify(body),
  });

  return processResponse<EmptyObject, false>({ res });
}

export async function postDislike({ ...body }: PostDislikeReq) {
  const URI = `${API_SERVER}/${PREFIX}/v1/members/dislike`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "POST",
    cache: "no-cache",
    body: JSON.stringify(body),
  });

  return processResponse<EmptyObject, false>({ res });
}

export async function getLikeStatus({ kind, kindUuid }: GetLikeStatusReq) {
  const URI = `${API_SERVER}/${PREFIX}/v1/members/${kind}/${kindUuid}/like-status`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  return processResponse<LikeStatus, false>({ res });
}

export async function getDislikeStatus({
  kind,
  kindUuid,
}: GetDislikeStatusReq) {
  const URI = `${API_SERVER}/${PREFIX}/v1/members/${kind}/${kindUuid}/dislike-status`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  return processResponse<DislikeStatus, false>({ res });
}

export async function getLikes({ kind, ...query }: GetLikesReq) {
  const URI = `${API_SERVER}/${PREFIX}/v1/members/${kind}/likes?${toURLSearchParams(query)}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  return processResponse<string, true>({ res });
}

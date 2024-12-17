"use server";

import type {
  CategoryInterestsItem,
  GetCategoryInterests,
  GetHashtagInterests,
  HashtagInterestsItem,
  PostCategoryInterests,
  PostHashtagInterests,
  PutCategoryInterests,
  PutHashtagInterests,
} from "@/types/member/member-service";
import { getHeaders, processResponse } from "../common";

const API_SERVER = process.env.BASE_API_URL;
const PREFIX = "member-service";

export async function getHashtagInterests({ memberUuid }: GetHashtagInterests) {
  const URI = `${API_SERVER}/${PREFIX}/v1/members/${memberUuid}/interests/hashtags`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
    next: { tags: [`hashtag-interests-${memberUuid}`] },
  });

  return processResponse<HashtagInterestsItem[], false>({ res });
}

export async function putHashtagInterests({
  memberUuid,
  ...body
}: PutHashtagInterests) {
  const URI = `${API_SERVER}/${PREFIX}/v1/members/${memberUuid}/interests/hashtags`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "PUT",
    cache: "no-cache",
    body: JSON.stringify(body),
  });

  return processResponse<HashtagInterestsItem[], false>({
    res,
    revalidatedTags: [`hashtag-interests-${memberUuid}`],
  });
}

export async function postHashtagInterests({
  memberUuid,
  ...body
}: PostHashtagInterests) {
  const URI = `${API_SERVER}/${PREFIX}/v1/members/${memberUuid}/interests/hashtags`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "POST",
    cache: "no-cache",
    body: JSON.stringify(body),
  });

  return processResponse<HashtagInterestsItem[], false>({
    res,
    revalidatedTags: [`hashtag-interests-${memberUuid}`],
  });
}

export async function getCategoryInterests({
  memberUuid,
}: GetCategoryInterests) {
  const URI = `${API_SERVER}/${PREFIX}/v1/members/${memberUuid}/interests/categories`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  return processResponse<CategoryInterestsItem[], false>({ res });
}

export async function putCategoryInterests({
  memberUuid,
  ...body
}: PutCategoryInterests) {
  const URI = `${API_SERVER}/${PREFIX}/v1/members/${memberUuid}/interests/categories`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "PUT",
    cache: "no-cache",
    body: JSON.stringify(body),
  });

  return processResponse<CategoryInterestsItem[], false>({ res });
}

export async function postCategoryInterests({
  memberUuid,
  ...body
}: PostCategoryInterests) {
  const URI = `${API_SERVER}/${PREFIX}/v1/members/${memberUuid}/interests/categories`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "POST",
    cache: "no-cache",
    body: JSON.stringify(body),
  });

  return processResponse<CategoryInterestsItem[], false>({ res });
}

"use server";

import type {
  Contest,
  ContestPostVote,
  MediaContest,
} from "@/types/contest/contest";
import { toURLSearchParams } from "@/functions/utils";
import type { CommonRes, EmptyObject } from "@/types/common";
import { getHeaders, processResponse } from "../common";

const API_SERVER = process.env.BASE_API_URL;
const PREFIX = "contest-service";

export interface GetContestParam {
  sortBy?: string; // 정렬 기준
  nextCursor?: string; // 다음 페이지 커서
  pageSize?: number; // 페이지 크기
  pageNo?: number; // 페이지 번호
}

// 현재 진행중인 콘테스트 불러오기 api
export async function getContest({ ...query }: GetContestParam = {}) {
  const url = `${API_SERVER}/${PREFIX}/v1/contests/view?${toURLSearchParams(query)}`;

  const res = await fetch(url, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  // console.log("데이터", res);
  return processResponse<Contest, true>({ res });
}

// 콘테스트 참여 api 로직
export async function contestPost({ contestId, media }: MediaContest) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/contests/${contestId}/apply`;
  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "POST",
    cache: "no-cache",
    body: JSON.stringify({ media }),
  });

  return processResponse<EmptyObject, false>({ res });
}

// 지난 콘테스트 api
export async function getContestHistory({ ...query }: GetContestParam = {}) {
  const url = `${API_SERVER}/${PREFIX}/v1/contests/history?${toURLSearchParams(query)}`;

  // console.log(url);

  const res: Response = await fetch(url, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  // console.log("히스토리", res.status);
  // console.log("히스토리", res.body);
  // console.log("히스토리", res.headers);
  // console.log("히스토리", res.url);
  // console.log("히스토리", res.type);
  return processResponse<Contest, true>({ res });
}

// 콘테스트 투표 api 로직
export async function contestPostVote(data: ContestPostVote) {
  const URI = `${API_SERVER}/${PREFIX}/auth/v1/contests/posts/vote`;
  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "POST",
    body: JSON.stringify(data),
    cache: "no-cache",
  });

  const responseData = (await res.json()) as CommonRes<ContestPostVote>;
  // console.log("데이터", responseData);

  return responseData;
}

export async function validateContestImg({ ...query }: { imgUrl: string }) {
  const url = `${API_SERVER}/${PREFIX}/admin/v1/contests/validate?${toURLSearchParams(query)}`;

  const res = await fetch(url, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  // console.log("데이터", res);
  return processResponse<boolean, false>({ res });
}

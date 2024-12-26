"use server";

import { toURLSearchParams } from "@/functions/utils";
import type { ContestPostList } from "@/types/contest/contest";
import { getHeaders, processResponse } from "../common";

const API_SERVER = process.env.BASE_API_URL;
const PREFIX = "contest-read-service";

export interface GetContestListParam {
  contestId: number;
  sortBy?: string; // 정렬 기준
  nextCursor?: string; // 다음 페이지 커서
  pageSize?: number; // 페이지 크기
  pageNo?: number; // 페이지 번호
}
// 콘테스트 리스트 조회 api
export async function getContestList({
  contestId,
  ...query
}: GetContestListParam) {
  const url = `${API_SERVER}/${PREFIX}/auth/v1/contests/view/${contestId}?${toURLSearchParams(query)}`;

  const res: Response = await fetch(url, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  // console.log("데이터가 오니", res);
  return processResponse<ContestPostList, true>({ res });
}

"use server";

import type { CommonRes, Result } from "@/types/contest/contest";
import { getHeaders } from "../common";

const API_SERVER = process.env.BASE_API_URL;
const PREFIX = "contest-service";

export interface GetContestParam {
  sortBy?: string; // 정렬 기준
  nextCursor?: string; // 다음 페이지 커서
  pageSize?: number; // 페이지 크기
  pageNo?: number; // 페이지 번호
}
//콘테스트
// export async function getMemberNickName() {
//   const URI = `${API_SERVER}/${PREFIX}/v1/contest/{contestId}`;
//   console.log("API 호출 시작: ", URI);

//   const res: Response = await fetch(URI, {
//     headers: await getHeaders(),
//     method: "GET",
//     cache: "no-cache",
//   });
//   console.error(`HTTP 에러 발생: ${res.status} - ${res.statusText}`);
//   console.error(`HTTP 에러 발생: ${res.status} - ${res.status}`);

//   const data = (await res.json()) as CommonRes<ContestListRes>;
//   console.log("데이터", data);

//   return data;
// }

//콘테스트 참여 api 로직
// export async function contestPost() {
//   const URI = `${API_SERVER}/${PREFIX}/auth/v1/contests/{contestId}/apply`;
//   console.log("API 호출 시작: ", URI);

//   const res: Response = await fetch(URI, {
//     headers: await getHeaders(),
//     method: "POST",
//     cache: "no-cache",
//   });
//   console.error(`HTTP 에러 발생: ${res.status} - ${res.statusText}`);
//   console.error(`HTTP 에러 발생: ${res.status} - ${res.status}`);

// const data = (await res.json()) as CommonRes<ContestId>;
//   console.log("데이터", data);

//   return data;
// }

// 현재 진행중인 콘테스트 불러오기 api
export async function getContest({
  sortBy = "latest",
  nextCursor,
  pageSize = 1,
  pageNo = 1,
}: GetContestParam = {}): Promise<CommonRes<Result>> {
  const queryParams = new URLSearchParams();

  // 파라미터 추가
  if (sortBy) queryParams.append("sortBy", sortBy);
  if (nextCursor) queryParams.append("nextCursor", nextCursor);
  queryParams.append("pageSize", pageSize.toString());
  queryParams.append("pageNo", pageNo.toString());

  const url = `${API_SERVER}/${PREFIX}/auth/v1/contests/view?${queryParams.toString()}`;

  const res: Response = await fetch(url, {
    headers: await getHeaders(),
    method: "GET",
    cache: "no-cache",
  });

  const data = (await res.json()) as CommonRes<Result>;

  // console.log("데이터", data);
  // console.log("데이터", data.result);
  return data;
}

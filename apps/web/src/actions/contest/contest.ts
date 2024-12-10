// "use server";

// import type { Pagination } from "@/types/common";
// import { commonPaginationRes } from "@/types/common/dummy";
// import type { Contest } from "@/types/contest/contest";

// const API_SERVER = process.env.BASE_API_URL;
// const PREFIX = "contest-service";

// interface GetAllContestParam {
//   pageSize: number;
//   pageNo: number;
// }
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

//현재 진행중인 콘테스트 불러오기 api
// export async function getContest(
//   { pageSize, pageNo }: GetAllContestParam = { pageSize: 1, pageNo: 1 }
// ) {
//   const res: Response = await fetch(
//     `${API_SERVER}/${PREFIX}/auth/v1/contests/view`,
//     {
//       method: "GET",
//       cache: "no-cache",
//     }
//   );

//   const contest = (await res.json()) as Contest[];

//   const { isSuccess, result } = commonPaginationRes({
//     content: contest,
//     pageSize,
//     pageNo,
//   });

//   if (!isSuccess) {
//     throw Error(result as string);
//   }

//   console.log(result);
//   return result as Pagination<Contest>;
// }

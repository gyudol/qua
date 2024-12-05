// "use server";

// import type { CommonRes } from "@/types/common";
// import type { ContestListRes } from "@/types/contest/contest";
// import { getHeaders } from "../common";

// const API_SERVER = process.env.BASE_API_URL;
// const PREFIX = "contest-service";

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

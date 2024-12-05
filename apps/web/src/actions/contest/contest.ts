// "use server";

// import type { CommonRes } from "@/types/common";
// import { getHeaders } from "../common";

// const API_SERVER = process.env.BASE_API_URL;
// const PREFIX = "contest-service";

// export async function getMemberNickName() {
//   const URI = `${API_SERVER}/${PREFIX}/v1/contest/{contestId}`;

//   const res: Response = await fetch(URI, {
//     headers: await getHeaders(),
//     method: "GET",
//     cache: "no-cache",
//   });

//   const data = (await res.json()) as CommonRes<ContestListRes>;
// }

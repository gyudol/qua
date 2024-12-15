"use server";

import type { GetGradeReq, Grade } from "@/types/reward-service";
import { getHeaders, processResponse } from "../common";

const API_SERVER = process.env.BASE_API_URL;
const PREFIX = "reward-service";

export async function getAllGrade() {
  const URI = `${API_SERVER}/${PREFIX}/v1/grade`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "force-cache",
  });

  return processResponse<Grade[], false>({ res });
}

export async function getGrade({ gradeId }: GetGradeReq) {
  const URI = `${API_SERVER}/${PREFIX}/v1/grade/${gradeId}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "force-cache",
  });

  return processResponse<Grade, false>({ res });
}

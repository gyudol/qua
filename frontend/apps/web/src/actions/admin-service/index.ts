"use server";

import type {
  Category,
  DeleteCategoryReq,
  GetCategoryReq,
  PostCategoryReq,
  PutCategoryReq,
} from "@/types/admin";
import type { EmptyObject } from "@/types/common";
import { getHeaders, processResponse } from "../common/index";

const API_SERVER = process.env.BASE_API_URL;
const PREFIX = "admin-service";

export async function getAllCategories() {
  const URI = `${API_SERVER}/${PREFIX}/v1/category`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "force-cache",
  });

  return processResponse<Category[], false>({ res });
}

export async function getCategory({ categoryId }: GetCategoryReq) {
  const URI = `${API_SERVER}/${PREFIX}/v1/category/${categoryId}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "GET",
    cache: "force-cache",
  });

  return processResponse<Category, false>({ res });
}

export async function putCategory({ categoryId, ...body }: PutCategoryReq) {
  const URI = `${API_SERVER}/${PREFIX}/v1/category/${categoryId}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "PUT",
    body: JSON.stringify(body),
  });

  return processResponse<EmptyObject, false>({ res });
}

export async function postCategory({ categoryId, ...body }: PostCategoryReq) {
  const URI = `${API_SERVER}/${PREFIX}/v1/category/${categoryId}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "POST",
    body: JSON.stringify(body),
  });

  return processResponse<EmptyObject, false>({ res });
}

export async function deleteCategory({ categoryId }: DeleteCategoryReq) {
  const URI = `${API_SERVER}/${PREFIX}/v1/category/${categoryId}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: "DELETE",
    cache: "no-cache",
  });

  return processResponse<EmptyObject, false>({ res });
}

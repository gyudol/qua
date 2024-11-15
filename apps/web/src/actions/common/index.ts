"use server";

import { revalidateTag } from "next/cache";
import type { ResponseType, ResultType } from "@/types/common";

interface ProcessResponseParam {
  res: Response;
  revalidatedTags?: string | string[];
}

export default async function processResponse<T, IsPagination extends boolean>({
  res,
  revalidatedTags,
}: ProcessResponseParam) {
  const { isSuccess, result, ..._rest } = (await res.json()) as ResponseType<
    T,
    IsPagination
  >;

  if (!isSuccess) {
    throw Error(result as string);
  }

  if (revalidatedTags) {
    if (Array.isArray(revalidatedTags)) {
      revalidatedTags.map(revalidateTag);
    } else {
      revalidateTag(revalidatedTags);
    }
  }

  return result as ResultType<T, IsPagination>;
}

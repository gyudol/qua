"use server";

import type { Pagination } from "@/types/common";
import { commonPaginationRes, commonRes } from "@/types/common/dummy";
import type { Feed } from "@/types/contents";

const API_SERVER = process.env.JSON_SERVER;

interface GetAllFeedParam {
  pageSize: number;
  pageNo: number;
}

export async function getAllFeed(
  { pageSize, pageNo }: GetAllFeedParam = { pageSize: 3, pageNo: 0 },
): Promise<Pagination<Feed>> {
  const res: Response = await fetch(`${API_SERVER}/feeds`, {
    cache: "no-cache",
  });

  const feeds = (await res.json()) as Feed[];

  // const {isSuccess, result} = await res.json() as CommonPaginationRes<Feed>;
  const { isSuccess, result } = commonPaginationRes({
    content: feeds,
    pageSize,
    pageNo,
  });

  if (!isSuccess) {
    throw Error();
  }

  // console.log(result);
  return result;
}

export async function getFeed(feedUuid: string): Promise<Feed> {
  const res: Response = await fetch(
    `${API_SERVER}/feeds?feedUuid=${feedUuid}`,
    {
      cache: "no-cache",
    },
  );

  const feed = (await res.json()) as Feed[];

  // const {isSuccess, result} = await res.json() as CommonPaginationRes<Feed>;
  const { isSuccess, result } = commonRes(feed);

  if (!isSuccess) {
    throw Error();
  }

  // eslint-disable-next-line no-console -- for test
  console.log(result);
  return result[0];
}

'use server';

import type { CommonRes, Pagination } from '@/types/common';
import { commonPaginationRes, commonRes } from '@/types/common/dummy';
import type { Feed } from '@/types/contents';
import type { CreateFeedType } from '@/types/request/requestType';

const API_SERVER = process.env.JSON_SERVER;
const BASE_API_URL = process.env.BASE_API_URL;

interface GetAllFeedParam {
  pageSize: number;
  pageNo: number;
}

export async function getAllFeed(
  { pageSize, pageNo }: GetAllFeedParam = { pageSize: 3, pageNo: 0 }
) {
  const res: Response = await fetch(`${API_SERVER}/feeds`, {
    cache: 'no-cache',
  });

  const feeds = (await res.json()) as Feed[];

  // const {isSuccess, result} = await res.json() as CommonPaginationRes<Feed>;

  const { isSuccess, result } = commonPaginationRes({
    content: feeds,
    pageSize,
    pageNo,
  });

  if (!isSuccess) {
    throw Error(result as string);
  }

  // console.log(result);
  return result as Pagination<Feed>;
}

export async function getFeed(feedUuid: string) {
  const res: Response = await fetch(
    `${API_SERVER}/feeds?feedUuid=${feedUuid}`,
    {
      cache: 'no-cache',
    }
  );

  const feed = (await res.json()) as Feed[];

  // const {isSuccess, result} = await res.json() as CommonPaginationRes<Feed>;
  const { isSuccess, result } = commonRes(feed);

  if (!isSuccess) {
    throw Error(result as string);
  }

  // console.log(result);
  return (result as Feed[])[0];
}

export async function createFeed(
  payload: CreateFeedType
): Promise<CommonRes<null>> {
  // console.log(payloade);
  // console.log(BASE_API_URL);
  const res: Response = await fetch(
    `${BASE_API_URL}/feed-service/auth/v1/feeds`,
    {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(payload),
    }
  );

  const result = (await res.json()) as CommonRes<null>;
  if (!result.isSuccess) {
    throw Error('피드 생성에 실패했습니다');
  }

  return result;
}

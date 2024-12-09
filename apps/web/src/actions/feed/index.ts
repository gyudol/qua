'use server';

import type { CommonRes, Pagination } from '@/types/common';
import { commonPaginationRes, commonRes } from '@/types/common/dummy';
import type { Feed } from '@/types/contents';
import type { CreateFeedType } from '@/types/request/requestType';
import { getHeaders, getSessionMemberUuid } from '../common';

const API_SERVER = process.env.JSON_SERVER;
const BASE_API_URL = process.env.BASE_API_URL;
const PREFIX = 'feed-service';

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

export async function createFeed(payload: CreateFeedType): Promise<boolean> {
  const memberUuid = await getSessionMemberUuid();
  payload.memberUuid = memberUuid;

  // console.log(payload);
  // console.log(BASE_API_URL);

  const URI = `${BASE_API_URL}/${PREFIX}/auth/v1/feeds`;
  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: 'POST',
    body: JSON.stringify(payload),
  });

  const { isSuccess } = (await res.json()) as CommonRes<null>;

  return isSuccess;
}

'use server';

import { toURLSearchParams } from '@/functions/utils';
import type {
  GetShortsRecommentsReq,
  ShortsRecomment,
} from '@/types/comment/comment-read-service';
import { getHeaders, processResponse } from '../common';

const API_SERVER = process.env.BASE_API_URL;
const PREFIX = 'comment-read-service';

export async function getShortsRecomments({
  commentUuid,
  ...query
}: GetShortsRecommentsReq) {
  const URI = `${API_SERVER}/${PREFIX}/v1/shorts/comments/${commentUuid}/recomments?${toURLSearchParams(query)}`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: 'GET',
    cache: 'no-cache',
  });

  return processResponse<ShortsRecomment, true>({ res });
}

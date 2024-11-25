'use server';

import type {
  Comment,
  CommentReqParam,
  CommentsReqParam,
  PostCommentParam,
  PutCommentParam,
  TargetType,
} from '@/types/comment-service';
import processResponse from '../common';
// import { options } from '@/app/api/auth/[...nextauth]/authOption';
// import { getServerSession } from 'next-auth';

const API_SERVER = process.env.BASE_API_URL;
const Prefix = 'comment-service';

function uri4Single<T extends TargetType, U extends boolean>({
  targetType,
  isRecomment,
  commentUuid,
  recommentUuid,
  isAuthRequired = false,
}: CommentReqParam<T, U> & {
  isAuthRequired?: boolean;
}) {
  if (isRecomment)
    return `${API_SERVER}/${Prefix}${isAuthRequired ? '/auth' : ''}/v1/${targetType}/comments/recomments/${recommentUuid}`;
  return `${API_SERVER}/${Prefix}${isAuthRequired ? '/auth' : ''}/v1/${targetType}/comments/${commentUuid}`;
}

function uri4Many<T extends TargetType, U extends boolean>({
  targetType,
  isRecomment,
  commentUuid,
  feedUuid,
  shortsUuid,
  isAuthRequired = false,
}: CommentsReqParam<T, U> & {
  isAuthRequired?: boolean;
}) {
  if (isRecomment)
    return `${API_SERVER}/${Prefix}${isAuthRequired ? '/auth' : ''}/v1/${targetType}/comments/${commentUuid}/recomments`;
  if (targetType === 'feeds')
    return `${API_SERVER}/${Prefix}${isAuthRequired ? '/auth' : ''}/v1/${targetType}/${feedUuid}/comments`;
  // if (targetType === "shorts")
  return `${API_SERVER}/${Prefix}${isAuthRequired ? '/auth' : ''}/v1/${targetType}/${shortsUuid}/comments`;
}

const headers = {
  'Content-Type': 'application/json',
  'Member-Uuid': 'member-001',
};

export async function GetComment<
  T extends TargetType,
  IsRecomment extends boolean,
>(param: CommentReqParam<T, IsRecomment>) {
  const URI = uri4Single<T, IsRecomment>(param);

  const res: Response = await fetch(URI, {
    headers,
    method: 'GET',
    cache: 'no-cache',
  });

  return processResponse<Comment<T, IsRecomment>, false>({ res });
}

export async function GetComments<
  T extends TargetType,
  IsRecomment extends boolean,
>({
  searchParams = '',
  ...param
}: CommentsReqParam<T, IsRecomment> & {
  searchParams?: string;
}) {
  const URI = uri4Many<T, IsRecomment>(param);
  // const session = await getServerSession(options);

  const res: Response = await fetch(`${URI}${searchParams}`, {
    headers,
    method: 'GET',
    next: {
      tags: ['postComment'],
    },
  });

  return processResponse<Comment<T, IsRecomment>, true>({ res });
}

export async function PostComment<
  T extends TargetType,
  IsRecomment extends boolean,
>({ body, ...param }: PostCommentParam<T, IsRecomment>) {
  'use server';

  const URI = uri4Many<T, IsRecomment>({ ...param, isAuthRequired: true });

  const res: Response = await fetch(URI, {
    headers,
    method: 'POST',
    body: JSON.stringify(body),
    cache: 'no-cache',
  });

  return processResponse<Record<string, never>, false>({
    res,
    revalidatedTags: 'postComment',
  });
}

export async function PutComment<
  T extends TargetType,
  IsRecomment extends boolean,
>({ body, ...param }: PutCommentParam<T, IsRecomment>) {
  const URI = uri4Single<T, IsRecomment>({ ...param, isAuthRequired: true });

  const res: Response = await fetch(URI, {
    headers,
    method: 'PUT',
    body: JSON.stringify(body),
    cache: 'no-cache',
  });

  return processResponse<Record<string, never>, false>({ res });
}

export async function DeleteComment<
  T extends TargetType,
  IsRecomment extends boolean,
>(param: CommentReqParam<T, IsRecomment>) {
  const URI = uri4Single<T, IsRecomment>({ ...param, isAuthRequired: true });

  const res: Response = await fetch(URI, {
    headers,
    method: 'DELETE',
    cache: 'no-cache',
  });

  return processResponse<Record<string, never>, false>({ res });
}

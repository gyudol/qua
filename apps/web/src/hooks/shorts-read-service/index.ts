"use client";

import { useInfiniteQuery, useQuery } from "@tanstack/react-query";
import {
  getMemberShortses,
  getMemberShortsRecs,
  getShorts,
  getShortsRecs,
} from "@/actions/shorts-read-service";
import type { ShortsReq } from "@/types/shorts/common";
import type {
  GetMemberShortsesReq,
  GetMemberShortsRecsReq,
  GetShortsRecsReq,
} from "@/types/shorts/shorts-read-service";

export function useGetShortsRecsInfiniteQuery({ ...query }: GetShortsRecsReq) {
  const { pageNo, pageSize, nextCursor } = query;
  return useInfiniteQuery({
    queryKey: [
      "shorts-service",
      {
        kind: "shorts-recommendation",
        query,
      },
    ],
    queryFn: ({ pageParam }) => getShortsRecs({ ...pageParam }),
    getNextPageParam: ({
      hasNext,
      ...nextQuery
    }: {
      pageNo: number;
      pageSize: number;
      nextCursor: string;
      hasNext: boolean;
    }) => (hasNext ? { ...nextQuery } : null),
    initialPageParam: {
      pageNo: pageNo || 1,
      pageSize: pageSize || 10,
      nextCursor: nextCursor || undefined,
    },
  });
}

export function useGetShortsQuery({ shortsUuid }: ShortsReq) {
  return useQuery({
    queryKey: [
      "shorts-service",
      {
        kind: "shorts",
        kindUuid: shortsUuid,
      },
    ],
    queryFn: () => getShorts({ shortsUuid }),
  });
}

export function useGetMemberShortsesInfiniteQuery({
  memberUuid,
  sortBy,
  ...query
}: GetMemberShortsesReq) {
  const { pageNo, pageSize, nextCursor } = query;
  return useInfiniteQuery({
    queryKey: [
      "shorts-service",
      {
        kind: "member-shortses",
        author: memberUuid,
        sortBy,
        query,
      },
    ],
    queryFn: ({ pageParam }) =>
      getMemberShortses({ memberUuid, sortBy, ...pageParam }),
    getNextPageParam: ({
      hasNext,
      ...nextQuery
    }: {
      pageNo: number;
      pageSize: number;
      nextCursor: string;
      hasNext: boolean;
    }) => (hasNext ? { ...nextQuery } : null),
    initialPageParam: {
      pageNo: pageNo || 1,
      pageSize: pageSize || 10,
      nextCursor: nextCursor || undefined,
    },
  });
}

export function useGetMemberShortsRecsInfiniteQuery({
  memberUuid,
  ...query
}: GetMemberShortsRecsReq) {
  const { pageNo, pageSize, nextCursor } = query;
  return useInfiniteQuery({
    queryKey: [
      "shorts-service",
      {
        kind: "member-shorts-recs",
        memberUuid,
        query,
      },
    ],
    queryFn: ({ pageParam }) =>
      getMemberShortsRecs({ memberUuid, ...pageParam }),
    getNextPageParam: ({
      hasNext,
      ...nextQuery
    }: {
      pageNo: number;
      pageSize: number;
      nextCursor: string;
      hasNext: boolean;
    }) => (hasNext ? { ...nextQuery } : null),
    initialPageParam: {
      pageNo: pageNo || 1,
      pageSize: pageSize || 10,
      nextCursor: nextCursor || undefined,
    },
  });
}

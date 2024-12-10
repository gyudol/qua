"use client";

import {
  useInfiniteQuery,
  useMutation,
  useQuery,
  useQueryClient,
} from "@tanstack/react-query";
import {
  getMemberShortses,
  getMemberShortsRecs,
  getShorts,
  getShortsRecs,
} from "@/actions/shorts-read-service";
import type { ShortsReq } from "@/types/shorts/common";
import type {
  GetMemberShortsesReq,
  GetShortsRecsReq,
} from "@/types/shorts/shorts-read-service";
import { useSessionContext } from "@/context/SessionContext";
import { deleteShorts } from "@/actions/shorts-service";

export function useGetShortsRecsInfiniteQuery({ ...query }: GetShortsRecsReq) {
  const { memberUuid } = useSessionContext();
  const { pageNo, pageSize, nextCursor } = query;
  return useInfiniteQuery({
    queryKey: [
      "shorts-service",
      {
        kind: "shorts-recommendation",
        query,
      },
    ],
    queryFn: ({ pageParam }) =>
      memberUuid
        ? getMemberShortsRecs({ memberUuid, ...pageParam })
        : getShortsRecs({ ...pageParam }),
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

export function useDeleteShorts({ shortsUuid }: ShortsReq) {
  const QC = useQueryClient();
  const queryKey = ["shorts-service"];
  return useMutation({
    mutationFn: async () => {
      await deleteShorts({ shortsUuid });
    },
    onSettled: async () => {
      await QC.invalidateQueries({ queryKey });
    },
  });
}

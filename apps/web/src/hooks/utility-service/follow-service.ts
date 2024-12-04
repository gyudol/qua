"use client";

import {
  useInfiniteQuery,
  useMutation,
  useQuery,
  useQueryClient,
} from "@tanstack/react-query";
import {
  deleteFollowing,
  getFollowers,
  getFollowings,
  getFollowStatus,
  postFollowing,
} from "@/actions/utility-service";
import type {
  FollowingReq,
  GetFollowersReq,
  GetFollowingsReq,
  GetFollowStatusReq,
} from "@/types/utility-service";

export function useFollowMutation({ sourceUuid, targetUuid }: FollowingReq) {
  const QC = useQueryClient();
  const QK = [
    "follow-service",
    {
      type: "follow-status",
      sourceUuid,
      targetUuid,
    },
  ];
  return useMutation({
    mutationFn: async (bool: boolean) => {
      bool
        ? await postFollowing({ sourceUuid, targetUuid })
        : await deleteFollowing({ sourceUuid, targetUuid });
    },
    onMutate: (bool: boolean) => {
      const prevStatus = QC.getQueryData<boolean>(QK);
      QC.setQueryData(QK, bool);
      return { prevStatus };
    },
    onError: (error, variables, context) => {
      QC.setQueryData(QK, context?.prevStatus);
    },
    onSettled: () => {
      void QC.invalidateQueries({ queryKey: QK });
    },
  });
}

export function useFollowingsInfiniteQuery({
  memberUuid,
  ...query
}: GetFollowingsReq) {
  const { nextCursor, pageSize, pageNo } = query;
  return useInfiniteQuery({
    queryKey: [
      "follow-service",
      {
        type: "followings",
        memberUuid,
        query,
      },
    ],
    queryFn: ({ pageParam }) => getFollowings({ memberUuid, ...pageParam }),
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
      nextCursor: nextCursor || "",
    },
  });
}

export function useFollowersInfiniteQuery({
  memberUuid,
  ...query
}: GetFollowersReq) {
  const { nextCursor, pageSize, pageNo } = query;
  return useInfiniteQuery({
    queryKey: [
      "follow-service",
      {
        type: "followers",
        memberUuid,
        query,
      },
    ],
    queryFn: ({ pageParam }) => getFollowers({ memberUuid, ...pageParam }),
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
      nextCursor: nextCursor || "",
    },
  });
}

export function useFollowStatusQuery({
  sourceUuid,
  targetUuid,
}: GetFollowStatusReq) {
  return useQuery({
    queryKey: [
      "follow-service",
      {
        type: "follow-status",
        sourceUuid,
        targetUuid,
      },
    ],
    queryFn: () => getFollowStatus({ sourceUuid, targetUuid }),
  });
}

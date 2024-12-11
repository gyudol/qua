import { useInfiniteQuery, useQuery } from "@tanstack/react-query";
import {
  getMemberPoint,
  getMemberPointHistory,
} from "@/actions/reward-service";
import type {
  GetMemberPointHistoryReq,
  GetMemberPointReq,
} from "@/types/reward-service";

export function useGetMemberPoint({ memberUuid }: GetMemberPointReq) {
  return useQuery({
    queryKey: [
      "reward-service",
      { type: "point", subtype: "member-point", memberUuid },
    ],
    queryFn: () => getMemberPoint({ memberUuid }),
  });
}

export function useGetMemberPointHistoryInfiniteQuery({
  memberUuid,
  ...query
}: GetMemberPointHistoryReq) {
  const { sortBy, pageNo, pageSize, nextCursor } = query;
  return useInfiniteQuery({
    queryKey: [
      "reward-service",
      {
        type: "point",
        subtype: "point-history",
        query,
      },
    ],
    queryFn: ({ pageParam }) =>
      getMemberPointHistory({ memberUuid, sortBy, ...pageParam }),
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

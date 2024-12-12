import { useInfiniteQuery } from "@tanstack/react-query";
import type { GetContestParam } from "@/actions/contest/contest";
import { getContestHistory } from "@/actions/contest/contest";

export function useGetContestInfiniteQuery({ ...query }: GetContestParam) {
  const { sortBy, pageNo, pageSize, nextCursor } = query;
  return useInfiniteQuery({
    queryKey: [
      "contest-service",
      {
        query,
      },
    ],
    queryFn: ({ pageParam }) => getContestHistory({ sortBy, ...pageParam }),
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

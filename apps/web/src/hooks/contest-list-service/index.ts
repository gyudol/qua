import { useInfiniteQuery } from "@tanstack/react-query";
import type { GetContestListParam } from "@/actions/contest-read-service";
import { getContestList } from "@/actions/contest-read-service";

export function useGetContestInfiniteQuery({ ...query }: GetContestListParam) {
  const { contestId, sortBy, pageNo, pageSize, nextCursor } = query;
  return useInfiniteQuery({
    queryKey: [
      "contest-service",
      {
        query,
      },
    ],
    queryFn: ({ pageParam }) =>
      getContestList({ contestId, sortBy, ...pageParam }),
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
      pageSize: pageSize || 8,
      nextCursor: nextCursor || "",
    },
  });
}

export function useGetConstestListInfiniteQuery({
  ...query
}: GetContestListParam) {
  const { contestId, sortBy, pageNo, pageSize, nextCursor } = query;
  return useInfiniteQuery({
    queryKey: [
      "contest-service",
      {
        kind: "conest-List",
        query,
      },
    ],
    queryFn: ({ pageParam }) =>
      getContestList({ contestId, sortBy, ...pageParam }),
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
      pageSize: pageSize || 8,
      nextCursor: nextCursor || undefined,
    },
  });
}

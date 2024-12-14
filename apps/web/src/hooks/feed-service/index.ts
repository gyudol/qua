"use client";

import {
  useInfiniteQuery,
  useMutation,
  useQuery,
  useQueryClient,
} from "@tanstack/react-query";
import {
  getFeed,
  getFeeds,
  getMemberFeeds,
  getRandomHashtags,
} from "@/actions/feed-read-service";
import type {
  GetFeedsReq,
  GetMemberFeeds,
  GetRandomHashtags,
} from "@/types/feed/feed-read-service";
import type { FeedReq } from "@/types/feed/common";
import { deleteFeed } from "@/actions/feed-service";

export function useGetFeedsInfiniteQuery({ ...query }: GetFeedsReq) {
  const { categoryName, hashtagName, sortBy, pageNo, pageSize, nextCursor } =
    query;
  return useInfiniteQuery({
    queryKey: [
      "feed-service",
      {
        kind: "feeds",
        query,
      },
    ],
    queryFn: ({ pageParam }) =>
      getFeeds({ categoryName, hashtagName, sortBy, ...pageParam }),
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
      pageSize: pageSize || 12,
      nextCursor: nextCursor || undefined,
    },
  });
}

export function useGetFeedQuery({ feedUuid }: FeedReq) {
  return useQuery({
    queryKey: [
      "feed-service",
      {
        kind: "feed",
        kindUuid: feedUuid,
      },
    ],
    queryFn: () => getFeed({ feedUuid }),
  });
}

export function useGetMemberFeedsInfiniteQuery({ ...query }: GetMemberFeeds) {
  const { memberUuid, sortBy, pageNo, pageSize, nextCursor } = query;
  return useInfiniteQuery({
    queryKey: [
      "feed-service",
      {
        kind: "member-feeds",
        query,
      },
    ],
    queryFn: ({ pageParam }) =>
      getMemberFeeds({ memberUuid, sortBy, ...pageParam }),
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
      pageSize: pageSize || 12,
      nextCursor: nextCursor || undefined,
    },
  });
}

export function useDeleteFeed({ feedUuid }: FeedReq) {
  const QC = useQueryClient();
  const queryKey = ["feed-service", { kind: "feeds" }];
  return useMutation({
    mutationFn: async () => {
      await deleteFeed({ feedUuid });
    },
    onSettled: async () => {
      await QC.invalidateQueries({ queryKey });
    },
  });
}

export function useGetRamdomHashtags({ size }: GetRandomHashtags) {
  return useQuery({
    queryKey: [],
    queryFn: () => getRandomHashtags({ size }),
  });
}

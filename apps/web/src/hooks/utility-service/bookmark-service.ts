"use client";

import {
  useInfiniteQuery,
  useMutation,
  useQuery,
  useQueryClient,
} from "@tanstack/react-query";
import {
  deleteFeedBookmark,
  deleteShortsBookmark,
  getFeedBookmarks,
  getFeedBookmarkStatus,
  getShortsBookmarks,
  getShortsBookmarkStatus,
  postFeedBookmark,
  postShortsBookmark,
} from "@/actions/utility-service";
import type { ShortsReq } from "@/types/shorts/common";
import type {
  GetFeedBookmarksReq,
  GetShortsBookmarksReq,
} from "@/types/utility-service";
import type { FeedReq } from "@/types/feed/common";
import { useSessionContext } from "@/context/SessionContext";

export function useShortsBookmarksInfiniteQuery({
  ...query
}: GetShortsBookmarksReq) {
  const { nextCursor, pageSize, pageNo } = query;
  return useInfiniteQuery({
    queryKey: [
      "bookmark-service",
      {
        type: "shorts-bookmarks",
        query,
      },
    ],
    queryFn: ({ pageParam }) => getShortsBookmarks({ ...pageParam }),
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

export function useShortsBookmarkMutation({ shortsUuid }: ShortsReq) {
  const QC = useQueryClient();
  const QK = [
    "bookmark-service",
    {
      type: "shorts-bookmark-status",
      uuid: shortsUuid,
    },
  ];
  return useMutation({
    mutationFn: async (bool: boolean) => {
      bool
        ? await postShortsBookmark({ shortsUuid })
        : await deleteShortsBookmark({ shortsUuid });
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

export function useShortsBookmarkStatusQuery({ shortsUuid }: ShortsReq) {
  return useQuery({
    queryKey: [
      "bookmark-service",
      {
        type: "shorts-bookmark-status",
        uuid: shortsUuid,
      },
    ],
    queryFn: () => getShortsBookmarkStatus({ shortsUuid }),
  });
}

export function useFeedBookmarksInfiniteQuery({
  ...query
}: GetFeedBookmarksReq) {
  const { nextCursor, pageSize, pageNo } = query;
  return useInfiniteQuery({
    queryKey: [
      "bookmark-service",
      {
        type: "feed-bookmarks",
        query,
      },
    ],
    queryFn: ({ pageParam }) => getFeedBookmarks({ ...pageParam }),
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

export function useFeedBookmarkMutation({ feedUuid }: FeedReq) {
  const QC = useQueryClient();
  const QK = [
    "bookmark-service",
    {
      type: "feed-bookmark-status",
      uuid: feedUuid,
    },
  ];
  return useMutation({
    mutationFn: async (bool: boolean) => {
      bool
        ? await postFeedBookmark({ feedUuid })
        : await deleteFeedBookmark({ feedUuid });
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

export function useFeedBookmarkStatusQuery({ feedUuid }: FeedReq) {
  const { isAuthenticated } = useSessionContext();
  return useQuery({
    queryKey: [
      "bookmark-service",
      {
        type: "feed-bookmark-status",
        uuid: feedUuid,
      },
    ],
    queryFn: () => {
      if (!isAuthenticated) return false;
      return getFeedBookmarkStatus({ feedUuid });
    },
  });
}

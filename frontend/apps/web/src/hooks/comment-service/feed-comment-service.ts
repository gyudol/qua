"use client";

import {
  useInfiniteQuery,
  useMutation,
  useQuery,
  useQueryClient,
} from "@tanstack/react-query";
import { getFeedComments } from "@/actions/comment-read-service";
import {
  deleteFeedComment,
  getFeedComment,
  postFeedComment,
  putFeedComment,
} from "@/actions/comment-service";
import type { FeedReq } from "@/types/feed/common";
import type { CommentReq } from "@/types/comment/common";
import type { FeedComment as RFeedComment } from "@/types/comment/comment-read-service";
import type { FeedComment } from "@/types/comment/comment-service";

export function useGetFeedCommentsInfiniteQuery({ feedUuid }: FeedReq) {
  return useInfiniteQuery({
    queryKey: [
      "comment-service",
      { kind: "feed-comments", kindUuid: feedUuid },
    ],
    queryFn: ({ pageParam }) => getFeedComments({ feedUuid, ...pageParam }),
    getNextPageParam: ({
      hasNext,
      ...query
    }: {
      pageNo: number;
      pageSize: number;
      hasNext: boolean;
    }) => (hasNext ? { ...query } : null),
    initialPageParam: { pageNo: 1, pageSize: 10 },
  });
}

export function useGetFeedCommentQuery({ commentUuid }: CommentReq) {
  return useQuery({
    queryKey: [
      "comment-service",
      { kind: "feed-comment", kindUuid: commentUuid },
    ],
    queryFn: () => getFeedComment({ commentUuid }),
  });
}

export function usePutFeedCommentMutation({ commentUuid }: CommentReq) {
  const QC = useQueryClient();
  const queryKey = [
    "comment-service",
    { kind: "feed-comment", kindUuid: commentUuid },
  ];
  return useMutation({
    mutationFn: async (content: string) => {
      const prevComment = QC.getQueryData<FeedComment>(queryKey);
      if (prevComment) {
        QC.setQueryData<FeedComment>(queryKey, { ...prevComment, content });
      }
      await putFeedComment({ commentUuid, content });
    },
    onMutate: async () => {
      await QC.cancelQueries({ queryKey });
      const prevComment = QC.getQueryData<FeedComment>(queryKey);

      return { prevComment };
    },
    onError: (error, variables, context) => {
      QC.setQueryData<FeedComment>(queryKey, context?.prevComment);
    },
    onSettled: async () => {
      await QC.invalidateQueries({ queryKey });
    },
  });
}

export function useDeleteFeedCommentMutation({ commentUuid }: CommentReq) {
  const QC = useQueryClient();
  const queryKey = [
    "comment-service",
    { kind: "feed-comment", kindUuid: commentUuid },
  ];
  return useMutation({
    mutationFn: async () => {
      await deleteFeedComment({ commentUuid });
    },
    onMutate: async () => {
      await QC.cancelQueries({ queryKey });
      const prevComment = QC.getQueryData<FeedComment>(queryKey);
      QC.setQueryData<null>(queryKey, null);
      return { prevComment };
    },
    onError: (error, variables, context) => {
      QC.setQueryData<FeedComment>(queryKey, context?.prevComment);
    },
  });
}

export function usePostFeedCommentQuery({ feedUuid }: FeedReq) {
  return useQuery<RFeedComment[]>({
    queryKey: [
      "comment-service",
      { kind: "new-feed-comment", kindUuid: feedUuid },
    ],
    queryFn: () => [],
  });
}

export function usePostFeedCommentMutation({ feedUuid }: FeedReq) {
  const QC = useQueryClient();
  return useMutation({
    mutationFn: ({
      content,
      memberUuid,
    }: {
      content: string;
      memberUuid: string;
    }) => {
      return postFeedComment({ feedUuid, content, memberUuid });
    },
    onSettled: (data) => {
      if (!data) return;
      const prev =
        QC.getQueryData<RFeedComment[]>([
          "comment-service",
          { kind: "new-feed-comment", kindUuid: feedUuid },
        ]) || [];

      QC.setQueryData<RFeedComment[]>(
        ["comment-service", { kind: "new-feed-comment", kindUuid: feedUuid }],
        [
          { ...data, likeCount: 0, dislikeCount: 0, recommentCount: 0 },
          ...prev,
        ],
      );
    },
  });
}

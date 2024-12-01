"use client";

import {
  useInfiniteQuery,
  useMutation,
  useQuery,
  useQueryClient,
} from "@tanstack/react-query";
import { getFeedRecomments } from "@/actions/comment-read-service";
import {
  deleteFeedRecomment,
  getFeedRecomment,
  postFeedRecomment,
  putFeedRecomment,
} from "@/actions/comment-service";
import type { CommentReq, RecommentReq } from "@/types/comment/common";
import type { FeedRecomment as RFeedRecomment } from "@/types/comment/comment-read-service";
import type { FeedRecomment } from "@/types/comment/comment-service";

export function useGetFeedRecommentsInfiniteQuery({ commentUuid }: CommentReq) {
  return useInfiniteQuery({
    queryKey: [
      "comment-service",
      { kind: "feed-recomments", kindUuid: commentUuid },
    ],
    queryFn: ({ pageParam }) =>
      getFeedRecomments({ commentUuid, ...pageParam }),
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

export function useGetFeedRecommentQuery({ recommentUuid }: RecommentReq) {
  return useQuery({
    queryKey: [
      "comment-service",
      { kind: "feed-recomment", kindUuid: recommentUuid },
    ],
    queryFn: () => getFeedRecomment({ recommentUuid }),
  });
}

export function usePutFeedRecommentMutation({ recommentUuid }: RecommentReq) {
  const QC = useQueryClient();
  const queryKey = [
    "comment-service",
    { kind: "feed-recomment", kindUuid: recommentUuid },
  ];
  return useMutation({
    mutationFn: async (content: string) => {
      const prevComment = QC.getQueryData<FeedRecomment>(queryKey);
      if (prevComment) {
        QC.setQueryData<FeedRecomment>(queryKey, { ...prevComment, content });
      }
      await putFeedRecomment({ recommentUuid, content });
    },
    onMutate: async () => {
      await QC.cancelQueries({ queryKey });
      const prevComment = QC.getQueryData<FeedRecomment>(queryKey);

      return { prevComment };
    },
    onError: (error, variables, context) => {
      QC.setQueryData<FeedRecomment>(queryKey, context?.prevComment);
    },
    onSettled: async () => {
      await QC.invalidateQueries({ queryKey });
    },
  });
}

export function useDeleteFeedRecommentMutation({
  recommentUuid,
}: RecommentReq) {
  const QC = useQueryClient();
  const queryKey = [
    "comment-service",
    { kind: "feed-recomment", kindUuid: recommentUuid },
  ];
  return useMutation({
    mutationFn: async () => {
      await deleteFeedRecomment({ recommentUuid });
    },
    onMutate: async () => {
      await QC.cancelQueries({ queryKey });
      const prevComment = QC.getQueryData<FeedRecomment>(queryKey);
      QC.setQueryData<null>(queryKey, null);
      return { prevComment };
    },
    onError: (error, variables, context) => {
      QC.setQueryData<FeedRecomment>(queryKey, context?.prevComment);
    },
  });
}

export function usePostFeedRecommentQuery({ commentUuid }: CommentReq) {
  return useQuery<RFeedRecomment[]>({
    queryKey: [
      "comment-service",
      { kind: "new-feed-recomment", kindUuid: commentUuid },
    ],
    queryFn: () => [],
  });
}

export function usePostFeedRecommentMutation({ commentUuid }: CommentReq) {
  const QC = useQueryClient();
  return useMutation({
    mutationFn: ({
      content,
      memberUuid,
    }: {
      content: string;
      memberUuid: string;
    }) => {
      return postFeedRecomment({ commentUuid, content, memberUuid });
    },
    onSettled: (data) => {
      if (!data) return;
      const prev =
        QC.getQueryData<RFeedRecomment[]>([
          "comment-service",
          { kind: "new-feed-recomment", kindUuid: commentUuid },
        ]) || [];

      QC.setQueryData<RFeedRecomment[]>(
        [
          "comment-service",
          { kind: "new-feed-recomment", kindUuid: commentUuid },
        ],
        [{ ...data, likeCount: 0, dislikeCount: 0 }, ...prev],
      );
    },
  });
}

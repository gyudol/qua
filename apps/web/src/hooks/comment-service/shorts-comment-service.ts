"use client";

import {
  useInfiniteQuery,
  useMutation,
  useQuery,
  useQueryClient,
} from "@tanstack/react-query";
import { getShortsComments } from "@/actions/comment-read-service";
import {
  deleteShortsComment,
  getShortsComment,
  postShortsComment,
  putShortsComment,
} from "@/actions/comment-service";
import type { CommentReq } from "@/types/comment/common";
import type { ShortsComment as RShortsComment } from "@/types/comment/comment-read-service";
import type { ShortsComment } from "@/types/comment/comment-service";
import type { ShortsReq } from "@/types/shorts-service";

export function useGetShortsCommentsInfiniteQuery({ shortsUuid }: ShortsReq) {
  return useInfiniteQuery({
    queryKey: [
      "comment-service",
      { kind: "shorts-comments", kindUuid: shortsUuid },
    ],
    queryFn: ({ pageParam }) => getShortsComments({ shortsUuid, ...pageParam }),
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

export function useGetShortsCommentQuery({ commentUuid }: CommentReq) {
  return useQuery({
    queryKey: [
      "comment-service",
      { kind: "shorts-comment", kindUuid: commentUuid },
    ],
    queryFn: () => getShortsComment({ commentUuid }),
  });
}

export function usePutShortsCommentMutation({ commentUuid }: CommentReq) {
  const QC = useQueryClient();
  const queryKey = [
    "comment-service",
    { kind: "shorts-comment", kindUuid: commentUuid },
  ];
  return useMutation({
    mutationFn: async (content: string) => {
      const prevComment = QC.getQueryData<ShortsComment>(queryKey);
      if (prevComment) {
        QC.setQueryData<ShortsComment>(queryKey, { ...prevComment, content });
      }
      await putShortsComment({ commentUuid, content });
    },
    onMutate: async () => {
      await QC.cancelQueries({ queryKey });
      const prevComment = QC.getQueryData<ShortsComment>(queryKey);

      return { prevComment };
    },
    onError: (error, variables, context) => {
      QC.setQueryData<ShortsComment>(queryKey, context?.prevComment);
    },
    onSettled: async () => {
      await QC.invalidateQueries({ queryKey });
    },
  });
}

export function useDeleteShortsCommentMutation({ commentUuid }: CommentReq) {
  const QC = useQueryClient();
  const queryKey = [
    "comment-service",
    { kind: "shorts-comment", kindUuid: commentUuid },
  ];
  return useMutation({
    mutationFn: async () => {
      await deleteShortsComment({ commentUuid });
    },
    onMutate: async () => {
      await QC.cancelQueries({ queryKey });
      const prevComment = QC.getQueryData<ShortsComment>(queryKey);
      QC.setQueryData<null>(queryKey, null);
      return { prevComment };
    },
    onError: (error, variables, context) => {
      QC.setQueryData<ShortsComment>(queryKey, context?.prevComment);
    },
  });
}

export function usePostShortsCommentQuery({ shortsUuid }: ShortsReq) {
  return useQuery<RShortsComment[]>({
    queryKey: [
      "comment-service",
      { kind: "new-shorts-comment", kindUuid: shortsUuid },
    ],
    queryFn: () => [],
  });
}

export function usePostShortsCommentMutation({ shortsUuid }: ShortsReq) {
  const QC = useQueryClient();
  return useMutation({
    mutationFn: ({
      content,
      memberUuid,
    }: {
      content: string;
      memberUuid: string;
    }) => {
      return postShortsComment({ shortsUuid, content, memberUuid });
    },
    onSettled: (data) => {
      if (!data) return;
      const prev =
        QC.getQueryData<RShortsComment[]>([
          "comment-service",
          { kind: "new-shorts-comment", kindUuid: shortsUuid },
        ]) || [];

      QC.setQueryData<RShortsComment[]>(
        [
          "comment-service",
          { kind: "new-shorts-comment", kindUuid: shortsUuid },
        ],
        [
          { ...data, likeCount: 0, dislikeCount: 0, recommentCount: 0 },
          ...prev,
        ],
      );
    },
  });
}

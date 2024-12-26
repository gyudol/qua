"use client";

import {
  useInfiniteQuery,
  useMutation,
  useQuery,
  useQueryClient,
} from "@tanstack/react-query";
import { getShortsRecomments } from "@/actions/comment-read-service";
import {
  deleteShortsRecomment,
  getShortsRecomment,
  postShortsRecomment,
  putShortsRecomment,
} from "@/actions/comment-service";
import type { CommentReq, RecommentReq } from "@/types/comment/common";
import type { ShortsRecomment as RShortsRecomment } from "@/types/comment/comment-read-service";
import type { ShortsRecomment } from "@/types/comment/comment-service";

export function getShortsRecommentsQK({ commentUuid }: CommentReq) {
  return [
    "comment-service",
    { kind: "shorts-recomments", kindUuid: commentUuid },
  ];
}

export function getShortsRecommentQK({ recommentUuid }: RecommentReq) {
  return [
    "comment-service",
    { kind: "shorts-recomment", kindUuid: recommentUuid },
  ];
}

export function getNewShortsRecommentsQK({ commentUuid }: CommentReq) {
  return [
    "comment-service",
    { kind: "new-shorts-recomment", kindUuid: commentUuid },
  ];
}

export function useGetShortsRecommentsInfiniteQuery({
  commentUuid,
}: CommentReq) {
  return useInfiniteQuery({
    queryKey: getShortsRecommentsQK({ commentUuid }),
    queryFn: ({ pageParam }) =>
      getShortsRecomments({ commentUuid, ...pageParam }),
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

export function useGetShortsRecommentQuery({ recommentUuid }: RecommentReq) {
  return useQuery({
    queryKey: getShortsRecommentQK({ recommentUuid }),
    queryFn: () => getShortsRecomment({ recommentUuid }),
  });
}

export function usePutShortsRecommentMutation({ recommentUuid }: RecommentReq) {
  const QC = useQueryClient();
  const queryKey = getShortsRecommentQK({ recommentUuid });
  return useMutation({
    mutationFn: async (content: string) => {
      const prevComment = QC.getQueryData<ShortsRecomment>(queryKey);
      if (prevComment) {
        QC.setQueryData<ShortsRecomment>(queryKey, { ...prevComment, content });
      }
      await putShortsRecomment({ recommentUuid, content });
    },
    onMutate: async () => {
      await QC.cancelQueries({ queryKey });
      const prevComment = QC.getQueryData<ShortsRecomment>(queryKey);

      return { prevComment };
    },
    onError: (error, variables, context) => {
      QC.setQueryData<ShortsRecomment>(queryKey, context?.prevComment);
    },
    onSettled: async () => {
      await QC.invalidateQueries({ queryKey });
    },
  });
}

export function useDeleteShortsRecommentMutation({
  recommentUuid,
}: RecommentReq) {
  const QC = useQueryClient();
  const queryKey = getShortsRecommentQK({ recommentUuid });
  return useMutation({
    mutationFn: async () => {
      await deleteShortsRecomment({ recommentUuid });
    },
    onMutate: async () => {
      await QC.cancelQueries({ queryKey });
      const prevComment = QC.getQueryData<ShortsRecomment>(queryKey);
      QC.setQueryData<null>(queryKey, null);
      return { prevComment };
    },
    onError: (error, variables, context) => {
      QC.setQueryData<ShortsRecomment>(queryKey, context?.prevComment);
    },
  });
}

export function usePostShortsRecommentQuery({ commentUuid }: CommentReq) {
  return useQuery<RShortsRecomment[]>({
    queryKey: getNewShortsRecommentsQK({ commentUuid }),
    queryFn: () => [],
  });
}

export function usePostShortsRecommentMutation({ commentUuid }: CommentReq) {
  const QC = useQueryClient();
  const QK = getNewShortsRecommentsQK({ commentUuid });
  return useMutation({
    mutationFn: ({
      content,
      memberUuid,
    }: {
      content: string;
      memberUuid: string;
    }) => {
      return postShortsRecomment({ commentUuid, content, memberUuid });
    },
    onSettled: (data) => {
      if (!data) return;
      const prev = QC.getQueryData<RShortsRecomment[]>(QK) || [];

      QC.setQueryData<RShortsRecomment[]>(QK, [
        { ...data, likeCount: 0, dislikeCount: 0 },
        ...prev,
      ]);
    },
  });
}

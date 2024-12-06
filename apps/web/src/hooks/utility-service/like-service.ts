"use client";

import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { useSession } from "next-auth/react";
import {
  getDislikeStatus,
  getLikeStatus,
  postDislike,
  postLike,
} from "@/actions/utility-service";
import type { KindReq } from "@/types/utility-service";

interface UseLikeServiceReq extends KindReq {
  likeCount: number;
  dislikeCount: number;
}

export function useLikeService({
  likeCount,
  dislikeCount,
  ...kindReq
}: UseLikeServiceReq) {
  const { status: sessionStaus, data } = useSession();
  const session = (data &&
    (data as { user?: { memberUuid?: string } }).user) || {
    memberUuid: "",
  };
  const memberUuid = session.memberUuid || "";

  const QC = useQueryClient();

  const likeStatusQK = [
    "like-service",
    { type: "status", subtype: "like", ...kindReq },
  ];

  const dislikeStatusQK = [
    "like-service",
    { type: "status", subtype: "dislike", ...kindReq },
  ];

  const AllStatusQK = ["like-service", { type: "status", ...kindReq }];

  const {
    data: likeStatus,
    isLoading: isLikeStatusLoading,
    isError: isLikeStatusError,
  } = useQuery<boolean>({
    queryKey: likeStatusQK,
    queryFn: () => getLikeStatus({ ...kindReq }),
  });

  const {
    data: dislikeStatus,
    isLoading: isDislikeStatusLoading,
    isError: isDislikeStatusError,
  } = useQuery<boolean>({
    queryKey: dislikeStatusQK,
    queryFn: () => getDislikeStatus({ ...kindReq }),
  });

  const postReq = { ...kindReq, memberUuid };

  function useStatusMutation(subtype: "like" | "dislike") {
    return useMutation({
      mutationFn: async () => {
        const prevLikeStatus = QC.getQueryData<boolean>(likeStatusQK);
        const prevDislikeStatus = QC.getQueryData<boolean>(dislikeStatusQK);

        let isLikeStatusChanged = false;
        let isDislikeStatusChanged = false;

        if (subtype === "like") {
          QC.setQueryData(likeStatusQK, (prev: boolean) => !prev);
        } else if (!prevDislikeStatus && prevLikeStatus) {
          isLikeStatusChanged = true;
          QC.setQueryData(likeStatusQK, false);
        }

        if (subtype === "dislike") {
          QC.setQueryData(dislikeStatusQK, (prev: boolean) => !prev);
        } else if (!prevLikeStatus && prevDislikeStatus) {
          isDislikeStatusChanged = true;
          QC.setQueryData(dislikeStatusQK, false);
        }

        if (subtype === "like" || isLikeStatusChanged)
          await postLike({ likeCount, ...postReq });

        if (subtype === "dislike" || isDislikeStatusChanged)
          await postDislike({ dislikeCount, ...postReq });
      },

      onMutate: async () => {
        await QC.cancelQueries({
          queryKey: AllStatusQK,
        });

        const prevLikeStatus = QC.getQueryData<boolean>(likeStatusQK);
        const prevDislikeStatus = QC.getQueryData<boolean>(dislikeStatusQK);

        return { prevLikeStatus, prevDislikeStatus };
      },

      onError: (error, variables, context) => {
        QC.setQueryData<boolean>(likeStatusQK, context?.prevLikeStatus);
        QC.setQueryData<boolean>(dislikeStatusQK, context?.prevDislikeStatus);
      },
      onSettled: () => {
        void QC.invalidateQueries({
          queryKey: AllStatusQK,
        });
      },
    });
  }

  const likeStatusMutation = useStatusMutation("like");
  const dislikeStatusMutation = useStatusMutation("dislike");
  const isReady =
    isLikeStatusLoading ||
    isDislikeStatusLoading ||
    isLikeStatusError ||
    isDislikeStatusError ||
    sessionStaus === "authenticated";

  return {
    likeStatus: { data: isReady && likeStatus, mutation: likeStatusMutation },
    dislikeStatus: {
      data: isReady && dislikeStatus,
      mutation: dislikeStatusMutation,
    },
  };
}

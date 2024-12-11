import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import {
  getAllBadges,
  getAllMemberBadges,
  getBadge,
  getMemberBadgeStatus,
  postMemberBadge,
  putMemberBadgeStatus,
} from "@/actions/reward-service";
import type {
  BadgeStatus,
  GetAllMemberBadgesReq,
  GetBadgeReq,
  GetMemberBadgeStatusReq,
  PostMemberBadgeReq,
  PutMemberBadgeStatusReq,
} from "@/types/reward-service";

export function useGetAllMemberBadges({ memberUuid }: GetAllMemberBadgesReq) {
  return useQuery({
    queryKey: [
      "reward-service",
      {
        type: "badge",
        subtype: "all-member-badges",
        memberUuid,
      },
    ],
    queryFn: () => getAllMemberBadges({ memberUuid }),
  });
}

export function useGetAllBadges() {
  return useQuery({
    queryKey: [
      "reward-service",
      {
        type: "badge",
        subtype: "all-badges",
      },
    ],
    queryFn: () => getAllBadges(),
  });
}

export function useGetMemberBadgeStatus({
  memberUuid,
  badgeId,
}: GetMemberBadgeStatusReq) {
  return useQuery({
    queryKey: [
      "reward-service",
      {
        type: "badge",
        subtype: "member-badge-status",
        memberUuid,
        badgeId,
      },
    ],
    queryFn: () => getMemberBadgeStatus({ memberUuid, badgeId }),
  });
}

export function useGetBadge({ badgeId }: GetBadgeReq) {
  return useQuery({
    queryKey: [
      "reward-service",
      {
        type: "badge",
        subtype: "badge",
        badgeId,
      },
    ],
    queryFn: () => getBadge({ badgeId }),
  });
}

export function usePutMemberBadgeStatusMutation({
  memberUuid,
}: Pick<PutMemberBadgeStatusReq, "memberUuid">) {
  const QC = useQueryClient();
  const queryKey = [
    "reward-service",
    {
      type: "badge",
      memberUuid,
    },
  ];
  return useMutation({
    mutationFn: async ({
      badgeId,
      equipped,
    }: Omit<PutMemberBadgeStatusReq, "memberUuid">) => {
      const prevBadgeStatus = QC.getQueryData<BadgeStatus>(queryKey);
      if (prevBadgeStatus) {
        QC.setQueryData<BadgeStatus>(queryKey, { badgeId, equipped });
      }
      await putMemberBadgeStatus({ memberUuid, badgeId, equipped });
    },
    onMutate: async () => {
      await QC.cancelQueries({ queryKey });
      const prevBadgeStatus = QC.getQueryData<BadgeStatus>(queryKey);

      return { prevBadgeStatus };
    },
    onError: (error, variables, context) => {
      QC.setQueryData<BadgeStatus>(queryKey, context?.prevBadgeStatus);
    },
    onSettled: async () => {
      await QC.invalidateQueries({ queryKey });
    },
  });
}

export function usePostMemberBadgeMutation({
  memberUuid,
}: Pick<PostMemberBadgeReq, "memberUuid">) {
  const QC = useQueryClient();
  const queryKey = [
    "reward-service",
    {
      type: "badge",
      subtype: "all-member-badges",
      memberUuid,
    },
  ];
  return useMutation({
    mutationFn: async ({ badgeId }: Omit<PostMemberBadgeReq, "memberUuid">) => {
      const prevAllMemberBadges = QC.getQueryData<BadgeStatus[]>(queryKey);
      if (prevAllMemberBadges) {
        QC.setQueryData<BadgeStatus[]>(queryKey, [
          ...prevAllMemberBadges,
          { badgeId, equipped: false },
        ]);
      }
      await postMemberBadge({ memberUuid, badgeId });
    },
    onMutate: async () => {
      await QC.cancelQueries({ queryKey });
      const prevAllMemberBadges = QC.getQueryData<BadgeStatus[]>(queryKey);

      return { prevAllMemberBadges };
    },
    onError: (error, variables, context) => {
      QC.setQueryData<BadgeStatus[]>(queryKey, context?.prevAllMemberBadges);
    },
    onSettled: async () => {
      await QC.invalidateQueries({ queryKey });
    },
  });
}

import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import {
  getCategoryInterests,
  getHashtagInterests,
  postCategoryInterests,
  postHashtagInterests,
  putCategoryInterests,
  putHashtagInterests,
} from "@/actions/member-service/interests";
import type {
  CategoryInterestsItem,
  GetCategoryInterests,
  GetHashtagInterests,
  HashtagInterestsItem,
  PostCategoryInterests,
  PostHashtagInterests,
  PutCategoryInterests,
  PutHashtagInterests,
} from "@/types/member/member-service";

export function useGetHashtagInterests({ memberUuid }: GetHashtagInterests) {
  return useQuery({
    queryKey: [
      "member-service",
      {
        type: "hashtag-interests",
        memberUuid,
      },
    ],
    queryFn: () => getHashtagInterests({ memberUuid }),
  });
}

export function usePutHashtagInterestsMutation({
  memberUuid,
}: Pick<PutHashtagInterests, "memberUuid">) {
  const QC = useQueryClient();
  const queryKey = [
    "member-service",
    {
      type: "hashtag-interests",
      memberUuid,
    },
  ];
  return useMutation({
    mutationFn: async ({
      hashtags,
    }: Omit<PutHashtagInterests, "memberUuid">) => {
      const prevHashtagInterests =
        QC.getQueryData<HashtagInterestsItem[]>(queryKey);
      if (prevHashtagInterests) {
        QC.setQueryData<HashtagInterestsItem[]>(queryKey, hashtags);
      }
      await putHashtagInterests({ memberUuid, hashtags });
    },
    onMutate: async () => {
      await QC.cancelQueries({ queryKey });
      const prevHashtagInterests =
        QC.getQueryData<HashtagInterestsItem[]>(queryKey);

      return { prevHashtagInterests };
    },
    onError: (error, variables, context) => {
      QC.setQueryData<HashtagInterestsItem[]>(
        queryKey,
        context?.prevHashtagInterests,
      );
    },
    onSettled: async () => {
      await QC.invalidateQueries({ queryKey });
    },
  });
}

export function usePostHashtagInterestsMutation({
  memberUuid,
}: Pick<PostHashtagInterests, "memberUuid">) {
  const QC = useQueryClient();
  const queryKey = [
    "member-service",
    {
      type: "hashtag-interests",
      memberUuid,
    },
  ];
  return useMutation({
    mutationFn: async ({
      hashtags,
    }: Omit<PostHashtagInterests, "memberUuid">) => {
      const prevHashtagInterests =
        QC.getQueryData<HashtagInterestsItem[]>(queryKey);
      if (prevHashtagInterests) {
        QC.setQueryData<HashtagInterestsItem[]>(queryKey, hashtags);
      }
      await postHashtagInterests({ memberUuid, hashtags });
    },
    onMutate: async () => {
      await QC.cancelQueries({ queryKey });
      const prevHashtagInterests =
        QC.getQueryData<HashtagInterestsItem[]>(queryKey);

      return { prevHashtagInterests };
    },
    onError: (error, variables, context) => {
      QC.setQueryData<HashtagInterestsItem[]>(
        queryKey,
        context?.prevHashtagInterests,
      );
    },
    onSettled: async () => {
      await QC.invalidateQueries({ queryKey });
    },
  });
}

export function useGetCategoryInterests({ memberUuid }: GetCategoryInterests) {
  return useQuery({
    queryKey: [
      "member-service",
      {
        type: "category-interests",
        memberUuid,
      },
    ],
    queryFn: () => getCategoryInterests({ memberUuid }),
  });
}

export function usePutCategoryInterestsMutation({
  memberUuid,
}: Pick<PutCategoryInterests, "memberUuid">) {
  const QC = useQueryClient();
  const queryKey = [
    "member-service",
    {
      type: "category-interests",
      memberUuid,
    },
  ];
  return useMutation({
    mutationFn: async ({
      categories,
    }: Omit<PutCategoryInterests, "memberUuid">) => {
      const prevCategoryInterests =
        QC.getQueryData<CategoryInterestsItem[]>(queryKey);
      if (prevCategoryInterests) {
        QC.setQueryData<CategoryInterestsItem[]>(queryKey, categories);
      }
      await putCategoryInterests({ memberUuid, categories });
    },
    onMutate: async () => {
      await QC.cancelQueries({ queryKey });
      const prevCategoryInterests =
        QC.getQueryData<CategoryInterestsItem[]>(queryKey);

      return { prevCategoryInterests };
    },
    onError: (error, variables, context) => {
      QC.setQueryData<CategoryInterestsItem[]>(
        queryKey,
        context?.prevCategoryInterests,
      );
    },
    onSettled: async () => {
      await QC.invalidateQueries({ queryKey });
    },
  });
}

export function usePostCategoryInterestsMutation({
  memberUuid,
}: Pick<PostCategoryInterests, "memberUuid">) {
  const QC = useQueryClient();
  const queryKey = [
    "member-service",
    {
      type: "category-interests",
      memberUuid,
    },
  ];
  return useMutation({
    mutationFn: async ({
      categories,
    }: Omit<PostCategoryInterests, "memberUuid">) => {
      const prevCategoryInterests =
        QC.getQueryData<CategoryInterestsItem[]>(queryKey);
      if (prevCategoryInterests) {
        QC.setQueryData<CategoryInterestsItem[]>(queryKey, categories);
      }
      await postCategoryInterests({ memberUuid, categories });
    },
    onMutate: async () => {
      await QC.cancelQueries({ queryKey });
      const prevCategoryInterests =
        QC.getQueryData<CategoryInterestsItem[]>(queryKey);

      return { prevCategoryInterests };
    },
    onError: (error, variables, context) => {
      QC.setQueryData<CategoryInterestsItem[]>(
        queryKey,
        context?.prevCategoryInterests,
      );
    },
    onSettled: async () => {
      await QC.invalidateQueries({ queryKey });
    },
  });
}

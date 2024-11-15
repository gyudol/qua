"use client";

import { useQuery } from "@tanstack/react-query";
import { GetComments } from "@/actions/comment-service";
import type {
  CommentsReqParam,
  GetCommentsSearchParams,
  TargetType,
} from "@/types/comment-service";
import { CommentInput } from "../molecules";
import { Comment } from "../organisms";

type CommentsSectionProp<
  T extends TargetType,
  IsRecomment extends boolean,
> = Omit<CommentsReqParam<T, IsRecomment>, "searchParams"> & {
  searchParams?: GetCommentsSearchParams<IsRecomment>;
};

export default function CommentSection<
  T extends TargetType,
  IsRecomment extends boolean,
>({
  targetType,
  isRecomment,
  shortsUuid,
  feedUuid,
  commentUuid,
  searchParams,
}: CommentsSectionProp<T, IsRecomment>) {
  const searchParamsString = searchParams
    ? `?${new URLSearchParams(Object.entries(searchParams)).toString()}`
    : "?sortBy=latest";

  const parentUuid = `${targetType}-${feedUuid || shortsUuid}${isRecomment ? `__comment-${commentUuid}` : ""}`;

  const {
    data: comments,
    isLoading,
    isError,
  } = useQuery({
    queryKey: ["comments", parentUuid, searchParamsString],
    queryFn: async () =>
      GetComments<T, IsRecomment>({
        targetType,
        isRecomment,
        shortsUuid,
        commentUuid,
        feedUuid,
        searchParams: searchParamsString,
      }),
  });

  if (isLoading) return "불러오는 중";

  if (isError) return "에러뜸";

  return (
    <section className="flex flex-col p-7 bg-white mb-[64px]">
      <div className="py-4 border-b">
        <h2 className="text-lg font-medium">23K 댓글</h2>
      </div>
      <div className="py-4 border-b">
        <CommentInput
          {...{ targetType, isRecomment, feedUuid, shortsUuid, commentUuid }}
        />
      </div>
      <div>
        {comments?.content.map(
          ({ commentUuid: _commentUuid, recommentUuid }) => (
            <Comment
              key={_commentUuid || recommentUuid}
              {...{
                targetType,
                isRecomment,
                commentUuid: _commentUuid,
                recommentUuid,
              }}
            />
          ),
        )}
      </div>
    </section>
  );
}

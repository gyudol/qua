"use client";

import { useState } from "react";
import type {
  FeedComment,
  FeedRecomment,
} from "@/types/comment/comment-read-service";
import { useGetFeedCommentQuery } from "@/hooks/comment-service";
import { CommentView } from "./comment-view";
import { RecommentViewList } from "./recomment-view-list";

type CommentThreadProps = FeedComment;

export function CommentThread({
  commentUuid,
  likeCount,
  dislikeCount,
  recommentCount,
}: CommentThreadProps) {
  const [recommentList, setRecommentList] = useState<FeedRecomment[]>([]);

  const { data: comment } = useGetFeedCommentQuery({ commentUuid });

  if (!comment) return null;

  return (
    <div>
      <CommentView
        {...{
          ...comment,
          likeCount,
          dislikeCount,
          recommentCount,
          setRecommentList,
        }}
      />
      <RecommentViewList
        {...{
          commentUuid,
          recommentList,
          setRecommentList,
        }}
      />
    </div>
  );
}

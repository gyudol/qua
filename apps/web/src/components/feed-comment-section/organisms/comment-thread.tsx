"use client";

import type { Dispatch, SetStateAction } from "react";
import { useState } from "react";
import type {
  FeedComment,
  FeedRecomment,
} from "@/types/comment/comment-read-service";
import { CommentView } from "./comment-view";
import { RecommentViewList } from "./recomment-view-list";

interface CommentThreadProps extends FeedComment {
  setCommentList: Dispatch<SetStateAction<FeedComment[]>>;
}

export function CommentThread({
  setCommentList,
  ...comment
}: CommentThreadProps) {
  const [recommentList, setRecommentList] = useState<FeedRecomment[]>([]);

  return (
    <div>
      <CommentView {...{ ...comment, setCommentList, setRecommentList }} />
      <RecommentViewList
        {...{
          commentUuid: comment.commentUuid,
          recommentList,
          setRecommentList,
        }}
      />
    </div>
  );
}

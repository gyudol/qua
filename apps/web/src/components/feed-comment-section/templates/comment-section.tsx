"use client";

import { useEffect, useState } from "react";
import { getFeedComments } from "@/actions/comment-read-service";
import type { FeedComment } from "@/types/comment/comment-read-service";
import { CommentThread } from "../organisms";
import { CommentInput } from "../atoms";

interface CommentSectionProps {
  feedUuid: string;
}

export function CommentSection({ feedUuid }: CommentSectionProps) {
  const [commentList, setCommentList] = useState<FeedComment[]>([]);

  useEffect(() => {
    void getFeedComments({ feedUuid }).then((res) => {
      setCommentList(() => [
        ...res.content.filter((comment) => !comment.deleted),
      ]);
    });
  }, [feedUuid]);

  return (
    <section>
      <CommentInput {...{ feedUuid, setCommentList }} />
      {commentList.map((comment) => (
        <CommentThread
          key={comment.commentUuid}
          {...{ ...comment, setCommentList }}
        />
      ))}
    </section>
  );
}

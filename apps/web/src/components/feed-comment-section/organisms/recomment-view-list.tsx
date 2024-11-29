"use client";

import type { Dispatch, SetStateAction } from "react";
import { useEffect } from "react";
import { getFeedRecomments } from "@/actions/comment-read-service";
import type { FeedRecomment } from "@/types/comment/comment-read-service";
import { RecommentView } from "./recomment-view";

interface RecommentViewListProps {
  commentUuid: string;
  recommentList: FeedRecomment[];
  setRecommentList: Dispatch<SetStateAction<FeedRecomment[]>>;
}

export function RecommentViewList({
  commentUuid,
  recommentList,
  setRecommentList,
}: RecommentViewListProps) {
  const _ = commentUuid;

  useEffect(() => {
    void getFeedRecomments({ commentUuid }).then((res) => {
      setRecommentList(() => [...res.content]);
    });
  }, [commentUuid, setRecommentList]);

  return (
    <div className="ml-[1.5rem]">
      {recommentList.map((recomment) => (
        <RecommentView
          key={recomment.recommentUuid}
          {...{ ...recomment, setRecommentList }}
        />
      ))}
    </div>
  );
}

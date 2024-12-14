"use client";

import React, { useState } from "react";
import { ChevronDown, ChevronUp, CornerDownRight } from "lucide-react";
import { useQueryClient } from "@tanstack/react-query";
import {
  getFeedRecommentsQK,
  getNewFeedRecommentsQK,
  useGetFeedRecommentsInfiniteQuery,
  usePostFeedRecommentQuery,
} from "@/hooks";
import { RecommentView } from "./recomment-view";

interface RecommentViewListProps {
  commentUuid: string;
  recommentCount: number;
}

export function RecommentViewList({
  commentUuid,
  recommentCount,
}: RecommentViewListProps) {
  const { data, hasNextPage, fetchNextPage } =
    useGetFeedRecommentsInfiniteQuery({ commentUuid });
  const { data: newRecommentList } = usePostFeedRecommentQuery({ commentUuid });
  const [isRecommentListShowed, setIsRecommentListShowed] = useState(false);
  const QC = useQueryClient();
  const newFeedRecommentsQK = getNewFeedRecommentsQK({ commentUuid });
  const feedRecommentsQK = getFeedRecommentsQK({ commentUuid });

  function handleShow() {
    if (isRecommentListShowed) {
      setIsRecommentListShowed(false);
      QC.setQueryData(newFeedRecommentsQK, []);
      void QC.invalidateQueries({ queryKey: feedRecommentsQK });
    } else {
      setIsRecommentListShowed(true);
    }
  }
  return (
    <div className="ml-[3.5rem] flex flex-col">
      {data?.pages[0].content.length ? (
        <div className="">
          <button
            type="button"
            onClick={handleShow}
            className="flex text-teal-400 gap-[1rem]"
          >
            {isRecommentListShowed ? (
              <ChevronUp size="1rem" />
            ) : (
              <ChevronDown size="1rem" />
            )}
            <span className="text-xs">
              답글 {recommentCount + (newRecommentList?.length || 0)}개
            </span>
          </button>
        </div>
      ) : null}
      <div>
        {newRecommentList?.map((recomment) => (
          <RecommentView key={recomment.recommentUuid} {...recomment} justNow />
        ))}
        {isRecommentListShowed ? (
          <>
            {data?.pages.map((page) => (
              <React.Fragment key={page.pageNo}>
                {page.content.map((recomment) => (
                  <RecommentView key={recomment.recommentUuid} {...recomment} />
                ))}
              </React.Fragment>
            ))}
            {hasNextPage ? (
              <button
                type="button"
                onClick={() => void fetchNextPage()}
                className="flex text-teal-400 gap-[1rem]"
              >
                <CornerDownRight size="1rem" />
                <span className="text-xs">답글 더보기</span>
              </button>
            ) : null}
          </>
        ) : null}
      </div>
    </div>
  );
}

"use client";

import React, { useState } from "react";
import { ChevronDown, ChevronUp, CornerDownRight } from "lucide-react";
import { useQueryClient } from "@tanstack/react-query";
import {
  getShortsRecommentsQK,
  getNewShortsRecommentsQK,
  useGetShortsRecommentsInfiniteQuery,
  usePostShortsRecommentQuery,
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
    useGetShortsRecommentsInfiniteQuery({ commentUuid });
  const { data: newRecommentList } = usePostShortsRecommentQuery({
    commentUuid,
  });
  const [isRecommentListShowed, setIsRecommentListShowed] = useState(false);
  const QC = useQueryClient();
  const newShortsRecommentsQK = getNewShortsRecommentsQK({ commentUuid });
  const shortsRecommentsQK = getShortsRecommentsQK({ commentUuid });

  function handleShow() {
    if (isRecommentListShowed) {
      setIsRecommentListShowed(false);
      QC.setQueryData(newShortsRecommentsQK, []);
      void QC.invalidateQueries({ queryKey: shortsRecommentsQK });
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

"use client";

import { useInfiniteScroll } from "@/hooks";
import { useGetMemberPointHistoryInfiniteQuery } from "@/hooks/reward-service";
import type { GetMemberPointHistoryReq } from "@/types/reward-service";
import { PointHistoryRecord } from "../organisms/PointHistoryRecord";
import { PointViewer } from "../organisms/PointViewer";

type PointHistorySectionProps = Pick<GetMemberPointHistoryReq, "memberUuid">;

export function PointHistorySection({ memberUuid }: PointHistorySectionProps) {
  const {
    data,
    status,
    error,
    hasNextPage,
    isFetchingNextPage,
    fetchNextPage,
  } = useGetMemberPointHistoryInfiniteQuery({
    memberUuid,
  });
  const objectRef = useInfiniteScroll({
    hasNextPage,
    isFetchingNextPage,
    fetchNextPage,
  });

  if (status === "error") throw Error(error.message);
  if (status === "pending") return null;

  return (
    <section>
      <PointViewer {...{ memberUuid }} />
      {data.pages[0].content.length ? (
        data.pages.map((page) =>
          page.content.map((record) => (
            <PointHistoryRecord key={record.id} {...record} />
          )),
        )
      ) : (
        <div>포인트 히스토리가 없습니다.</div>
      )}
      <div ref={objectRef} />
    </section>
  );
}

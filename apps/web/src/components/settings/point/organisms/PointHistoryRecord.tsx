import { PostedAt } from "@/components/common/atoms";
import type { PointHistoryRecord as PointHistoryRecordType } from "@/types/reward-service";

const pointReasonText = {
  FEED_CREATE: "피드 생성",
  SHORTS_CREATE: "쇼츠 생성",
  COMMENT_CREATE: "댓글 생성",
  CONTEST_WIN: "콘테스트 우승",
  CONTEST_JOIN: "콘테스트 참가",
  FEED_DELETED: "피드 삭제",
  SHORTS_DELETED: "쇼츠 삭제",
  COMMENT_DELETED: "댓글 삭제",
};

type PointHistoryRecordProps = PointHistoryRecordType;

export function PointHistoryRecord({
  createdAt,
  reason,
  pointChangeType,
  point,
}: PointHistoryRecordProps) {
  return (
    <div>
      <div>
        <PostedAt
          {...{
            createdAt,
            updatedAt: createdAt,
          }}
        />
      </div>
      <div>
        {pointReasonText[reason]}
        {pointChangeType === "INCREASE" ? " +" : " -"}
        {point}
      </div>
    </div>
  );
}

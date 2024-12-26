import type { CommonPaginationReq, Datetime } from "../common";

export interface GetMemberPointReq {
  memberUuid: string;
}

export interface GetMemberPointHistoryReq extends CommonPaginationReq {
  memberUuid: string;
  reason?: PointReason;
  pointChangeType?: PointChangeType;
  sortBy?: PointSortBy;
}

export interface PointHistoryRecord extends MemberPoint {
  id: number;
  memberUuid: string;
  reason: PointReason;
  pointChangeType: PointChangeType;
  createdAt: Datetime;
}

export interface MemberPoint {
  point: number;
}

export type PointReason =
  | "FEED_CREATE"
  | "SHORTS_CREATE"
  | "COMMENT_CREATE"
  | "CONTEST_WIN"
  | "CONTEST_JOIN"
  | "FEED_DELETED"
  | "SHORTS_DELETED"
  | "COMMENT_DELETED";

export type PointChangeType = "INCREASE" | "DECREASE";

export type PointSortBy = "lastest" | "oldest";

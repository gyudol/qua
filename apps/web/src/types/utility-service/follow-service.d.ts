import type { CommonPaginationReq } from "../common";
import type { MemberReq } from "../member";

interface FollowingReq {
  sourceUuid: string;
  targetUuid: string;
}

export type PostFollowingReq = FollowingReq;
export type DeleteFollowingReq = FollowingReq;
export type GetFollowStatusReq = FollowingReq;
export interface GetFollowingsReq extends MemberReq, CommonPaginationReq {}
export interface GetFollowersReq extends MemberReq, CommonPaginationReq {}

export type FollowStatus = boolean;

import type { CommonPaginationReq } from "../common";
import type { MemberReq } from "../member";

interface KindReq {
  kind: string;
  kindUuid: string;
}

export interface PostLikeReq extends MemberReq, KindReq {}
export interface PostDislikeReq extends MemberReq, KindReq {}

export type GetLikeStatusReq = KindReq;
export type GetDislikeStatusReq = KindReq;
export interface GetLikesReq extends CommonPaginationReq {
  kind: string;
}

export type LikeStatus = boolean;
export type DislikeStatus = boolean;

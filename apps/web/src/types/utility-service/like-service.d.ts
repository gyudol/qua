import type { CommonPaginationReq } from "../common";
import type { MemberReq } from "../member/common";

interface KindReq {
  kind: string;
  kindUuid: string;
}

export interface PostLikeReq extends MemberReq, KindReq {
  likeCount: number;
}
export interface PostDislikeReq extends MemberReq, KindReq {
  dislikeCount: number;
}

export type GetLikeStatusReq = KindReq;
export type GetDislikeStatusReq = KindReq;
export interface GetLikesReq extends CommonPaginationReq {
  kind: string;
}

export type LikeStatus = boolean;
export type DislikeStatus = boolean;

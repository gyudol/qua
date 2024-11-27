import type { BaseRecomment, CommentReq, GetRecommentsReq } from "../common";

export interface GetShortsRecommentsReq extends GetRecommentsReq, CommentReq {}

export interface ShortsRecomment extends BaseRecomment {
  likeCount: number;
  dislikeCount: number;
}

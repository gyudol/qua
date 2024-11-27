import type {
  BaseRecomment,
  CommentReq,
  DeleteRecommentReq,
  GetRecommentReq,
  GetRecommentsReq,
  PostRecommentReq,
  PutRecommentReq,
} from "../common";

export type GetShortsRecommentReq = GetRecommentReq;
export interface GetShortsRecommentsReq extends GetRecommentsReq, CommentReq {}
export interface PostShortsRecommentReq extends PostRecommentReq, CommentReq {}
export type PutShortsRecommentReq = PutRecommentReq;
export type DeleteShortsRecommentReq = DeleteRecommentReq;

export type ShortsRecomment = BaseRecomment;

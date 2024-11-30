import type {
  BaseRecomment,
  CommentReq,
  DeleteRecommentReq,
  GetRecommentReq,
  PostRecommentReq,
  PutRecommentReq,
} from "../common";

export type GetShortsRecommentReq = GetRecommentReq;
export interface PostShortsRecommentReq extends PostRecommentReq, CommentReq {}
export type PutShortsRecommentReq = PutRecommentReq;
export type DeleteShortsRecommentReq = DeleteRecommentReq;

export type ShortsRecomment = BaseRecomment;

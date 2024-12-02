import type {
  BaseRecomment,
  CommentReq,
  DeleteRecommentReq,
  GetRecommentReq,
  PostRecommentReq,
  PutRecommentReq,
} from "../common";

export type GetFeedRecommentReq = GetRecommentReq;
export interface PostFeedRecommentReq extends PostRecommentReq, CommentReq {}
export type PutFeedRecommentReq = PutRecommentReq;
export type DeleteFeedRecommentReq = DeleteRecommentReq;

export type FeedRecomment = BaseRecomment;

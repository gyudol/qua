import type {
  BaseRecomment,
  CommentReq,
  DeleteRecommentReq,
  GetRecommentReq,
  GetRecommentsReq,
  PostRecommentReq,
  PutRecommentReq,
} from "../common";

export type GetFeedRecommentReq = GetRecommentReq;
export interface GetFeedRecommentsReq extends GetRecommentsReq, CommentReq {}
export interface PostFeedRecommentReq extends PostRecommentReq, CommentReq {}
export type PutFeedRecommentReq = PutRecommentReq;
export type DeleteFeedRecommentReq = DeleteRecommentReq;

export type FeedRecomment = BaseRecomment;

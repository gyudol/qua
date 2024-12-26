import type { BaseRecomment, CommentReq, GetRecommentsReq } from "../common";

export interface GetFeedRecommentsReq extends GetRecommentsReq, CommentReq {}

export interface FeedRecomment extends BaseRecomment {
  likeCount: number;
  dislikeCount: number;
}

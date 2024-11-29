import type { FeedReq, FeedUuid } from "../../feed-service";
import type {
  BaseComment,
  DeleteCommentReq,
  GetCommentReq,
  GetCommentsReq,
  PostCommentReq,
  PutCommentReq,
} from "../common";

export type GetFeedCommentReq = GetCommentReq;
export interface GetFeedCommentsReq extends GetCommentsReq, FeedReq {}
export interface PostFeedCommentReq extends PostCommentReq, FeedReq {}
export type PutFeedCommentReq = PutCommentReq;
export type DeleteFeedCommentReq = DeleteCommentReq;

export interface FeedComment extends BaseComment {
  feedUuid: FeedUuid;
}

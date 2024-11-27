import type { ShortsReq, ShortsUuid } from "../../shorts-service";
import type {
  BaseComment,
  DeleteCommentReq,
  GetCommentReq,
  GetCommentsReq,
  PostCommentReq,
  PutCommentReq,
} from "../common";

export type GetShortsCommentReq = GetCommentReq;
export interface GetShortsCommentsReq extends GetCommentsReq, ShortsReq {}
export interface PostShortsCommentReq extends PostCommentReq, ShortsReq {}
export interface PutShortsCommentReq extends PutCommentReq, ShortsReq {}
export type DeleteShortsCommentReq = DeleteCommentReq;

export interface ShortsComment extends BaseComment {
  shortsUuid: ShortsUuid;
}

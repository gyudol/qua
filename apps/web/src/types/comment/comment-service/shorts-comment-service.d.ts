import type { ShortsReq, ShortsUuid } from "../../shorts-service";
import type {
  BaseComment,
  DeleteCommentReq,
  GetCommentReq,
  PostCommentReq,
  PutCommentReq,
} from "../common";

export type GetShortsCommentReq = GetCommentReq;
export interface PostShortsCommentReq extends PostCommentReq, ShortsReq {}
export type PutShortsCommentReq = PutCommentReq;
export type DeleteShortsCommentReq = DeleteCommentReq;

export interface ShortsComment extends BaseComment {
  shortsUuid: ShortsUuid;
}

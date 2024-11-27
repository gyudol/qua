import type { CommonPaginationReq, Datetime } from "../common";
import type { MemberReq, MemberUuid } from "../member";

interface Content {
  content: string;
}

interface SortBy {
  sortBy?: "latest" | "likes";
}

interface Base extends Content {
  memberUuid: MemberUuid;
  createdAt: Datetime;
  updatedAt: Datetime;
}

// Comment
export type CommentUuid = string;
export interface CommentReq {
  commentUuid: CommentUuid;
}

export type GetCommentReq = CommentReq;
export interface GetCommentsReq extends CommonPaginationReq, SortBy {}
export interface PostCommentReq extends MemberReq, Content {}
export interface PutCommentReq extends CommentReq, Content {}
export type DeleteCommentReq = CommentReq;

export interface BaseComment extends Base {
  commentUuid: CommentUuid;
}

// Recomment
export type RecommentUuid = string;
export interface RecommentReq {
  recommentUuid: RecommentUuid;
}

export type GetRecommentReq = RecommentReq;
export interface GetRecommentsReq extends CommonPaginationReq, SortBy {}
export interface PostRecommentReq extends MemberReq, Content {}
export interface PutRecommentReq extends RecommentReq, Content {}
export type DeleteRecommentReq = RecommentReq;

export interface BaseRecomment extends Base {
  commentUuid: CommentUuid;
  recommentUuid: RecommentUuid;
}

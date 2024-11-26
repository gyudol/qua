import type { Datetime } from "../common";
import type { Uuid } from "../contents";

type TargetType = "feeds" | "shorts";

interface TargetUuid<T extends TargetType> {
  shortsUuid?: T extends "shorts" ? string : never;
  feedUuid?: T extends "feeds" ? string : never;
}

interface CommentUuid<T extends boolean> {
  recommentUuid?: T extends true ? string : never;
  commentUuid?: T extends false ? string : never;
}

interface Comment<T extends TargetType, U extends boolean>
  extends TargetUuid<T>,
    CommentUuid<U> {
  memberUuid: Uuid;
  content: string;
  createdAt: Datetime;
  updatedAt: Datetime;
}

// Put
interface PutCommentReqBody {
  content: string;
}

type PutRecommentReqBody = PutCommentReqBody;

// Post
interface PostCommentReqBody {
  memberUuid: string;
  content: string;
}

type PostRecommentReqBody = PostCommentReqBody;

// GET
type SortBy = "latest" | "likes";
type NextCursor = string;
type PageSize = number;
type PageNo = number;

interface GetCommentsSearchParams<IsRecomment extends boolean> {
  sortBy?: IsRecomment extends false ? SortBy : never;
  nextCursor?: NextCursor;
  pageSize?: PageSize;
  pageNo?: PageNo;
}

interface CommentReqType<T extends TargetType, IsRecomment extends boolean> {
  targetType: T;
  isRecomment: IsRecomment;
}

interface CommentReqParam<T extends TargetType, IsRecomment extends boolean>
  extends CommentReqType<T, IsRecomment>,
    CommentUuid<IsRecomment> {}

interface CommentsReqParam<T extends TargetType, IsRecomment extends boolean>
  extends CommentReqType<T, IsRecomment>,
    TargetUuid<T> {
  commentUuid?: IsRecomment extends true ? string : never;
}

type PostCommentParam<
  T extends TargetType,
  IsRecomment extends boolean,
> = CommentsReqParam<T, IsRecomment> & {
  body: Omit<PostCommentReqBody, "memberUuid">;
};

type PutCommentParam<
  T extends TargetType,
  IsRecomment extends boolean,
> = CommentReqParam<T, IsRecomment> & { body: PutCommentReqBody };

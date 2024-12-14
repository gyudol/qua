import type { CommonPaginationReq } from "../common";
import type { FeedReq } from "../feed/common";
import type { ShortsReq } from "../shorts/common";

export type GetShortsBookmarksReq = CommonPaginationReq;
export type GetFeedBookmarksReq = CommonPaginationReq;
export type PostShortsBookmarkReq = ShortsReq;
export type PostFeedBookmarkReq = FeedReq;
export type DeleteShortsBookmarkReq = ShortsReq;
export type DeleteFeedBookmarkReq = FeedReq;
export type GetShortsBookmarkStatusReq = ShortsReq;
export type GetFeedBookmarkStatusReq = FeedReq;

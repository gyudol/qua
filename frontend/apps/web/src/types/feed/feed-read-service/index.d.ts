import type { CommonPaginationReq, Datetime } from "@/types/common";
import type { Hashtag } from "@/types/contents";
import type { ImageMedia, VideoMedia } from "@/types/media";
import type { FeedReq } from "../common";

export interface GetFeedsReq extends CommonPaginationReq {
  categoryName?: string;
  hashtagName?: string;
  sortBy?: "latest" | "likes";
}

export type GetFeedReq = FeedReq;

export interface GetMemberFeeds extends CommonPaginationReq {
  memberUuid: string;
  sortBy?: "latest" | "likes";
}

export interface SearchFeedsReq
  extends Omit<CommonPaginationReq, "nextCursor"> {
  keyword: string;
}

export interface GetRandomHashtags {
  size: number;
}

export interface GetFeedRecommentdationsReq {
  memberUuid: string;
  pageSize?: number;
  pageNo?: number;
}

export interface Feed {
  feedUuid: string;
  memberUuid: string;
  title: string;
  content: string;
  categoryName: string;
  visibility: "VISIBLE";
  hashtags: Hashtag[];
  mediaList: (ImageMedia | VideoMedia)[];
  likeCount: number;
  dislikeCount: number;
  commentCount: number;
  createdAt: Datetime;
  updatedAt: Datetime;
}

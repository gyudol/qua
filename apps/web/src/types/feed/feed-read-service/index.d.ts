import type { CommonPaginationReq, Datetime } from "@/types/common";
import type { Hashtag } from "@/types/contents";
import type { FeedReq, ImageMedia, VideoMedia } from "../common";

export interface GetFeedsReq extends CommonPaginationReq {
  categoryName?: string;
  hashtagName?: string;
  sortBy?: "latest" | "likes";
}

export type GetFeedReq = FeedReq;

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

import type { CommonPaginationReq, Datetime } from "@/types/common";
import type { Hashtag } from "@/types/contents";
import type { ShortsMedia } from "@/types/media";

export interface GetShortsReq {
  shortsUuid: string;
}

export type GetShortsRecsReq = CommonPaginationReq;

export interface GetMemberShortsesReq extends CommonPaginationReq {
  memberUuid: string;
  sortBy: "latest" | "likes";
}
export interface GetMemberShrotsRecsReq extends CommonPaginationReq {
  memberUuid: string;
}

export interface Shorts {
  shortsUuid: string;
  memberUuid: string;
  title: string;
  playtime: number;
  visibility: "VISIBLE";
  hashtags: Hashtag[];
  media: ShortsMedia;
  likeCount: number;
  dislikeCount: number;
  commentCount: number;
  createdAt: Datetime;
  updatedAt: Datetime;
}

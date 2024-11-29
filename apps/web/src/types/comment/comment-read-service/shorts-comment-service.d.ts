import type { ShortsReq, ShortsUuid } from "@/types/shorts-service";
import type { BaseComment, GetCommentsReq } from "../common";

export interface GetShortsCommentsReq extends GetCommentsReq, ShortsReq {}

export interface ShortsComment extends BaseComment {
  shortsUuid: ShortsUuid;
  likeCount: number;
  dislikeCount: number;
  recommentCount: number;
}

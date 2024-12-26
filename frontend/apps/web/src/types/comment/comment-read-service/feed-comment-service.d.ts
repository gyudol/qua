import type { FeedReq, FeedUuid } from "@/types/feed/common";
import type { BaseComment, GetCommentsReq } from "../common";

export interface GetFeedCommentsReq extends GetCommentsReq, FeedReq {}

export interface FeedComment extends BaseComment {
  feedUuid: FeedUuid;
  likeCount: number;
  dislikeCount: number;
  recommentCount: number;
}

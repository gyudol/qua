import type { Datetime } from "../common";
import type { Member } from "../member";
import type { ContentsStatistics, CUAt, Hashtag, Media, Uuid } from "./common";

export interface BaseFeed {
  feedUuid: Uuid;
}

export interface FeedAuthor extends BaseFeed, Member {}

export interface FeedStatistics extends BaseFeed, ContentsStatistics {}

export interface Feed extends BaseFeed, CUAt {
  author: Member;
  statistics: ContentsStatistics;
  title: string;
  content: string;
  mediaList: Media[];
  hashtags: Hashtag[];
  createdAt: Datetime;
  updatedAt: Datetime
}

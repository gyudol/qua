import type { Datetime } from "../common";
import type { Member } from "../member/common";
import type { ContentsStatistics, CUAt, Hashtag, Uuid } from "./common";

export interface BaseShorts {
  shortsUuid: Uuid;
}

export interface ShortsAuthor extends BaseShorts, Member {}

export interface ShortsStatistics extends BaseShorts, ContentsStatistics {}

export interface Shorts extends BaseShorts, CUAt {
  author: Member;
  statistics: ContentsStatistics;
  title: string;
  content: string;
  mediaUrl: string;
  playtime: number;
  hashtags: Hashtag[];
  createdAt: Datetime;
  updatedAt: Datetime;
}

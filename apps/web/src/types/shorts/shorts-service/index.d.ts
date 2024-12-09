import type { Hashtag } from "@/types/contents";
import type { ShortsMedia } from "@/types/media";

export interface PutShortsReq {
  shortsUuid: string;
  title: string;
  playtime: number;
}

export interface DeleteShortsReq {
  shortsUuid: string;
}

export interface PutShortsStatusReq {
  shortsUuid: string;
  visibility: "VISIBLE" | "HIDDEN" | "REPORTED";
}

export interface PutShortsHashtagsReq {
  shortsUuid: string;
  hashtags: Hashtag[];
}

export interface PostShortsReq {
  memberUuid: string;
  title: string;
  playtime: number;
  visibility: "VISIBLE";
  hashtags: Hashtag[];
  media: ShortsMedia;
}

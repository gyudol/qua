import type { Hashtag } from "../contents";

export interface FeedHashtag {
  name: string; // 해시태그 이름
}

export interface MediaListType {
  mediaUuid: string; // 미디어 URL
  mediaType: string; // 설명
  assets: ImageMediaType | VideoMediaType;
}

export interface MediaAsset {
  mediaUrl: string; // 미디어 URL
  description: string; // 설명
}

export interface ImageMediaType {
  IMAGE: MediaAsset;
}

export interface VideoMediaType {
  VIDEO_THUMBNAIL: MediaAsset;
  STREAMING_360: MediaAsset;
  STREAMING_540: MediaAsset;
  STREAMING_720: MediaAsset;
  VIDEO_MP4: MediaAsset;
}

export interface ResponseFeedType {
  feedUuid: string;
  title: string;
  content: string;
  hashtags: Hashtag[];
  mediaList: MediaListType;
  createdAt: string;
  updatedAt: string;
}

export interface CreateFeedType {
  memberUuid: string; // 회원 UUID
  title: string; // 피드 제목
  content: string; // 피드 내용
  categoryName: string; // 카테고리 이름 (일반 문자열)
  visibility: "VISIBLE" | "HIDDEN"; // 공개 여부
  hashtags: FeedHashtag[]; // 해시태그 목록
  mediaList: MediaListType[]; // 미디어 목록
}

export interface Media {
  mediaId: string; // 백엔드에서 따로 uuid 부여하지 않기로 했다고 함
  mediaType: string;
  mediaUrl: string;
}

export interface Hashtag {
  name: string;
}

export interface CUAt {
  createdAt: string;
  updatedAt: string;
}

export interface ContentsStatistics {
  likeCount: number;
  dislikeCount: number;
  commentCount: number;
  hitCount: number; // 조회수
}

export type Uuid = string;

export interface CommonRes<T> {
  httpStatus: {
    error: boolean;
    is4xxClientError: boolean;
    is5xxServerError: boolean;
    is1xxInformational: boolean;
    is2xxSuccessful: boolean;
    is3xxRedirection: boolean;
  };
  isSuccess: boolean;
  message: string;
  code: number;
  result: T | string;
}
// 현재 콘테스트 불러오기
interface Winner {
  memberUuid: string;
  postUuid: string;
  badgeId: number;
  voteCount: number;
  ranking: string;
}

interface Contest {
  contestId: number;
  contestName: string;
  startDate: string;
  endDate: string;
  imgUrl: string;
  winners: Winner[];
}
// 콘테스트 생성
interface MediaAsset {
  mediaUrl: string;
  description: string;
}
interface VideoAssets {
  VIDEO_THUMBNAIL: MediaAsset;
  STREAMING_360: MediaAsset;
  STREAMING_540: MediaAsset;
  STREAMING_720: MediaAsset;
  VIDEO_MP4: MediaAsset;
}
interface ImageAssets {
  IMAGE: MediaAsset;
}
interface VideoMedia {
  mediaUuid: string;
  mediaType: "VIDEO";
  assets: VideoAssets;
}
interface ImageMedia {
  mediaUuid: string;
  mediaType: "IMAGE";
  assets: ImageAssets;
}
type Media = VideoMedia | ImageMedia;
interface MediaContest {
  contestId: number;
  media: Media;
}

// 콘테스트 리스트

interface Asset {
  mediaUrl: string;
  description: string;
}

// Media 타입 정의
interface MediaList {
  mediaUuid: string;
  mediaType: "IMAGE" | "VIDEO";
  assets: Asset;
}

// 전체 데이터 구조 타입 정의
interface ContestPostList {
  postUuid: string;
  contestId: number;
  memberUuid: string;
  media: MediaList;
  createdAt: string;
  voteCount: number;
}

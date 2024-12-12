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
// 현재 콘테스트 타입
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
interface Result {
  content: Contest[];
  nextCursor: string;
  hasNext: boolean;
  pageSize: number;
  pageNo: number;
}

// 콘테스트 생성

export interface MediaAsset {
  mediaUrl: string;
  description: string;
}

export interface ImageMedia {
  media: {
    mediaUuid: string;
    mediaType: "IMAGE";
    assets: {
      IMAGE: MediaAsset;
    };
  };
}

export interface VideoMedia {
  media: {
    mediaUuid: string;
    mediaType: "VIDEO";
    assets: {
      VIDEO_THUMBNAIL: MediaAsset;
      STREAMING_360: MediaAsset;
      STREAMING_540: MediaAsset;
      STREAMING_720: MediaAsset;
      VIDEO_MP4: MediaAsset;
    };
  };
}
export type MediaType = ImageMedia | VideoMedia;

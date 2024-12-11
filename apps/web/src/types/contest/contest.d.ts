export interface ContestListRes {
  contestPostUuid: "string";
}
export interface ContestId {
  contestId: "string";
}

// 현재 콘테스트 타입
export interface ContestContent {
  contestId: number;
  contestName: string;
  imgUrl: string;
}

export interface Contest {
  content: ContestContent[];
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

export type FeedUuid = string;

export interface FeedReq {
  feedUuid: FeedUuid;
}

export interface MediaAsset {
  mediaUrl: string;
  description: string;
}

export interface ImageMediaAssets {
  IMAGE: MediaAsset;
}

export interface VideoMediaAssets {
  VIDEO_THUMBNAIL: MediaAsset;
  STREAMING_360: MediaAsset;
  STREAMING_540: MediaAsset;
  STREAMING_720: MediaAsset;
  VIDEO_MP4: MediaAsset;
}

export interface ImageMedia {
  mediaUuid: string;
  mediaType: "IMAGE";
  assets: ImageMediaAssets;
}
export interface VideoMedia {
  mediaUuid: string;
  mediaType: "VIDEO";
  assets: VideoMediaAssets;
}

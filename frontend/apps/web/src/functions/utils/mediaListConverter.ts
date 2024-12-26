import type { MediaListType } from '@/types/request/requestType';

export const MediaAssetConverter = (
  s3Url: string,
  getMediaType: string
): Promise<MediaListType> => {
  const splitUrl = s3Url.split('/');
  const getMediaUuid = splitUrl[splitUrl.length - 1].split('.')[0];
  const getFileExtention = splitUrl[splitUrl.length - 1].split('.')[1];

  if (getMediaType === 'IMAGE') {
    const result: MediaListType = {
      mediaUuid: getMediaUuid,
      mediaType: getMediaType,
      assets: {
        IMAGE: {
          mediaUrl: `image/${getMediaUuid}.${getFileExtention}`,
          description: '이미지 파일',
        },
      },
    };

    return Promise.resolve(result);
  }

  const result: MediaListType = {
    mediaUuid: getMediaUuid,
    mediaType: getMediaType,
    assets: {
      VIDEO_THUMBNAIL: {
        mediaUrl: `video/processed/${getMediaUuid}/Thumbnails/${getMediaUuid}.0000000.jpg`,
        description: '비디오 썸네일 이미지',
      },
      STREAMING_360: {
        mediaUrl: `video/processed/${getMediaUuid}/HLS/${getMediaUuid}_360.m3u8`,
        description: '640 * 360 해상도',
      },
      STREAMING_540: {
        mediaUrl: `video/processed/${getMediaUuid}/HLS/${getMediaUuid}_540.m3u8`,
        description: '960 * 540 해상도',
      },
      STREAMING_720: {
        mediaUrl: `video/processed/${getMediaUuid}/HLS/${getMediaUuid}_720.m3u8`,
        description: '1280 * 720 해상도',
      },
      VIDEO_MP4: {
        mediaUrl: `video/processed/${getMediaUuid}/MP4/${getMediaUuid}.mp4`,
        description: '비디오 MP4',
      },
    },
  };

  return Promise.resolve(result);
};

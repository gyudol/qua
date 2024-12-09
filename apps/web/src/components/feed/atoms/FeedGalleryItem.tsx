'use client';
import Image from 'next/image';
import Hls from 'hls.js';
import { useEffect, useState, useRef } from 'react';
import type { ImageMedia, VideoMedia } from '@/types/feed/common';

interface FeedGalleryItemProps {
  media: ImageMedia | VideoMedia;
  className?: string;
}

export function FeedGalleryItem({ media, className }: FeedGalleryItemProps) {
  const videoRef = useRef<HTMLVideoElement | null>(null);
  const [aspectRatio, setAspectRatio] = useState(16 / 9); // 기본 비율

  const { mediaType, assets } = media;

  useEffect(() => {
    if (mediaType === 'VIDEO' && videoRef.current) {
      const { mediaUrl } = assets.STREAMING_720;

      if (mediaUrl.endsWith('m3u8') && Hls.isSupported()) {
        const hls = new Hls();
        hls.loadSource(`https://media.qua.world/${mediaUrl}`);
        hls.attachMedia(videoRef.current);
        return () => hls.destroy();
      } else if (
        videoRef.current.canPlayType('application/vnd.apple.mpegurl')
      ) {
        // iOS Safari 지원
        videoRef.current.src = `https://media.qua.world/${mediaUrl}`;
      }
    }
  }, [mediaType, assets]);

  const handleMetadataLoaded = () => {
    if (videoRef.current) {
      const videoWidth = videoRef.current.videoWidth;
      const videoHeight = videoRef.current.videoHeight;
      setAspectRatio(videoWidth / videoHeight); // 비율 계산
      console.log('metadata loaded', videoWidth, videoHeight, aspectRatio);
      console.log(screen.width / (10 - aspectRatio));
    }
  };

  const togglePlayPause = () => {
    const video = videoRef.current;
    if (video) {
      if (video.paused) {
        void video.play();
      } else {
        video.pause();
      }
    }
  };

  if (mediaType === 'IMAGE') {
    const { mediaUrl, description } = assets.IMAGE;
    return (
      <figure className={`${className}`}>
        <Image
          alt={description}
          className="object-cover"
          height={800}
          priority
          width={800}
          src={`https://media.qua.world/${mediaUrl}`}
        />
      </figure>
    );
  }

  const { mediaUrl } = assets.STREAMING_540;

  return (
    <figure
      className={`${className} bg-white border-[1px] border-slate-200 max-w-[800px]`}
      style={{
        height: `auto`,
      }}
    >
      <video
        ref={videoRef}
        className="w-full h-full object-cover"
        preload="metadata"
        onLoadedMetadata={handleMetadataLoaded}
        onClick={() => togglePlayPause()}
        autoPlay
        muted
        loop
      >
        <source src={`https://media.qua.world/${mediaUrl}`} />
        <track kind="captions" default />
      </video>
    </figure>
  );
}

"use client";

import Hls from "hls.js";
import { useEffect, useRef } from "react";

interface ShortsVideoProp {
  src: string;
  isActive: boolean;
}

export default function ShortsVideo({ src, isActive }: ShortsVideoProp) {
  const videoRef = useRef<HTMLVideoElement | null>(null);

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

  useEffect(() => {
    if (src.endsWith("m3u8") && Hls.isSupported() && videoRef.current) {
      const hls = new Hls();
      hls.loadSource(src);
      hls.attachMedia(videoRef.current);
      return () => hls.destroy();
    } else if (
      videoRef.current &&
      videoRef.current.canPlayType("application/vnd.apple.mpegurl")
    ) {
      // iOS Safari 지원
      videoRef.current.src = src;
    }
  }, [src]);

  useEffect(() => {
    if (videoRef.current) {
      if (isActive) {
        void videoRef.current.play();
        videoRef.current.muted = false;
      } else {
        videoRef.current.pause();
        videoRef.current.muted = true;
        videoRef.current.currentTime = 0.05;
      }
    }
  }, [isActive]);

  return (
    <figure className="size-full">
      <video
        ref={videoRef}
        className="w-full h-full object-cover"
        preload="metadata"
        onClick={() => togglePlayPause()}
        autoPlay
        muted
        loop
      >
        <source src={src} />
        <track kind="captions" default />
      </video>
    </figure>
  );
}

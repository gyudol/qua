"use client";

import { useEffect } from "react";
import type { Shorts } from "@/types/shorts/shorts-read-service";
import { useCommentDrawerContext } from "@/context/DrawerContext";
import { ShortsButtonGroup, ShortsPannel } from "../molecules";
import ShortsVideo from "./ShortsVideo";

interface ShortsSlideContentProps extends Shorts {
  isActive: boolean;
}

export function ShortsSlideContent({
  isActive,
  ...shorts
}: ShortsSlideContentProps) {
  const src = `https://media.qua.world/${shorts.media.assets.STREAMING_720.mediaUrl}`;
  // const src = `https://media.qua.world/${shorts.media.assets.VIDEO_MP4.mediaUrl}`;

  const { setCommentTarget } = useCommentDrawerContext();
  useEffect(() => {
    if (isActive && setCommentTarget)
      setCommentTarget(() => {
        return {
          targetType: "shorts",
          targetUuid: shorts.shortsUuid,
        };
      });
  }, [isActive, setCommentTarget, shorts.shortsUuid]);

  return (
    <>
      <div className="w-full h-full flex items-center">
        <ShortsVideo {...{ src, isActive }} />
      </div>
      <ShortsButtonGroup {...shorts} />
      <ShortsPannel {...shorts} />
    </>
  );
}

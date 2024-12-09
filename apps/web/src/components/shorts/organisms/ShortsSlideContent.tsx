import type { Shorts } from "@/types/shorts/shorts-read-service";
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

  return (
    <>
      <div className="w-full flex items-center">
        <ShortsVideo {...{ src, isActive }} />
      </div>
      <ShortsButtonGroup {...shorts} />
      <ShortsPannel {...shorts} />
    </>
  );
}

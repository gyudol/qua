import type { Feed } from "@/types/feed/feed-read-service";
import { FeedGalleryItem } from "../atoms/FeedGalleryItem";

type FeedGalleryProps = Pick<Feed, "mediaList">;

export function FeedGallery({ mediaList }: FeedGalleryProps) {
  if (mediaList.length === 0) return null;

  return (
    <div className="w-full aspect-[1]">
      {mediaList.map((media) => (
        <FeedGalleryItem key={media.mediaUuid} {...{ ...media }} />
      ))}
    </div>
  );
}

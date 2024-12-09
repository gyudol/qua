import Image from "next/image";
import type { ImageMedia, VideoMedia } from "@/types/media";

type FeedGalleryItemProps = ImageMedia | VideoMedia;

export function FeedGalleryItem({ mediaType, assets }: FeedGalleryItemProps) {
  const { mediaUrl, description } =
    mediaType === "IMAGE" ? assets.IMAGE : assets.VIDEO_THUMBNAIL;

  return (
    <figure className="relative w-full h-full">
      <Image
        className="rounded-xl"
        src={`https://media.qua.world/${mediaUrl}`}
        alt={description}
        priority
        fill
      />
    </figure>
  );
}

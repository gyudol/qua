import Image from "next/image";
import type { ImageMedia, VideoMedia } from "@/types/feed/common";

type FeedGalleryItemProps = ImageMedia | VideoMedia;

export function FeedGalleryItem({ mediaType, assets }: FeedGalleryItemProps) {
  const { mediaUrl, description } =
    mediaType === "IMAGE" ? assets.IMAGE : assets.VIDEO_THUMBNAIL;

  return (
    <figure className="relative w-full h-full">
      <Image
        className="rounded-xl"
        src={`https://qua-assets.s3.ap-northeast-2.amazonaws.com/${mediaUrl}`}
        alt={description}
        fill
      />
    </figure>
  );
}

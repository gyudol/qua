import Image from "next/image";
import { FileText } from "lucide-react";
import type { Feed } from "@/types/feed/feed-read-service";

type FeedCompactThumbnailProps = Pick<Feed, "mediaList">;

export function FeedCompactThumbnail({ mediaList }: FeedCompactThumbnailProps) {
  const firstMedia = mediaList.length === 0 ? null : mediaList[0];
  if (firstMedia) {
    const { mediaType, assets } = firstMedia;
    const { mediaUrl, description } =
      mediaType === "IMAGE" ? assets.IMAGE : assets.VIDEO_THUMBNAIL;

    return (
      <figure className="relative w-[6rem] h-[6rem]">
        <Image
          className="rounded-xl"
          src={`https://media.qua.world/${mediaUrl}`}
          alt={description}
          sizes="6rem"
          priority
          fill
        />
      </figure>
    );
  }
  return (
    <figure className="relative w-[6rem] h-[6rem] flex justify-center items-center bg-primary rounded-xl">
      <FileText color="white" size="2rem" />
    </figure>
  );
}

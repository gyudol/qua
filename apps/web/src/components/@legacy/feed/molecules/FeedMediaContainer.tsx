import { cn } from "@repo/ui/lib/utils";
import type { Media } from "@/types/contents";
import { Fig } from "@/components/common/atoms";

interface FeedMediaContainerProp {
  mediaList: Media[];
}

function FeedMediaThumbnail({
  mediaId,
  mediaType,
  mediaUrl,
  className,
}: Media & { className: string }) {
  return mediaType === "image" ? (
    <Fig {...{ className, src: mediaUrl, alt: mediaId }} />
  ) : null;
}

export default function FeedMediaContainer({
  mediaList,
}: FeedMediaContainerProp) {
  return (
    <section
      className={cn(
        "grid gap-[4px] w-full aspect-[4/3]",
        `grid-rows-${mediaList.length > 1 ? 2 : 1}`,
        `grid-cols-${mediaList.length > 2 ? 2 : 1}`,
      )}
    >
      {mediaList.slice(0, 3).map(({ mediaId, mediaType, mediaUrl }, i) => {
        if (mediaType === "image")
          return (
            <FeedMediaThumbnail
              key={mediaId}
              className={cn(
                i === 0 && "row-start-1 row-span-2",
                i === 1 && "col-start-2 col-span-1 row-start-1 row-span-1",
                i === 2 && "col-start-2 col-span-1 row-start-2 row-span-1",
              )}
              {...{ mediaId, mediaType, mediaUrl }}
            />
          );
        return null;
      })}
    </section>
  );
}

import { Separator } from "@repo/ui/shadcn/separator";
import type { Feed } from "@/types/feed/feed-read-service";
import { FeedButtonGroup } from "../molecules/FeedButtonGroup";
import { FeedFooter } from "../molecules/FeedFooter";
import { FeedCompactThumbnail } from "../molecules/FeedCompactThumbnail";
import { FeedCompactHeader } from "../molecules/FeedCompactHeader";

type FeedCompactArticleProps = Feed;

export function FeedCompactArticle({
  feedUuid,
  memberUuid,
  createdAt,
  updatedAt,
  title,
  content: _,
  mediaList,
  likeCount,
  dislikeCount,
  commentCount,
  hashtags,
}: FeedCompactArticleProps) {
  return (
    <>
      <article className="p-[1rem] flex gap-[1rem]">
        <FeedCompactThumbnail {...{ mediaList }} />
        <div className="flex-1 flex flex-col">
          <FeedCompactHeader
            {...{ feedUuid, memberUuid, createdAt, updatedAt, title }}
          />
          <FeedButtonGroup
            {...{ feedUuid, likeCount, dislikeCount, commentCount }}
          />
          <FeedFooter {...{ hashtags }} />
        </div>
      </article>
      <Separator className="bg-[#EEE] h-[0.5rem]" />
    </>
  );
}

import { Separator } from "@repo/ui/shadcn/separator";
import type { Feed } from "@/types/feed/feed-read-service";
import { FeedHeader } from "../molecules/FeedHeader";
import { FeedContent } from "../molecules/FeedContent";
import { FeedGallery } from "../molecules/FeedGallery";
import { FeedButtonGroup } from "../molecules/FeedButtonGroup";
import { FeedFooter } from "../molecules/FeedFooter";

type FeedCardArticleProps = Feed;

export function FeedCardArticle({
  feedUuid,
  memberUuid,
  createdAt,
  updatedAt,
  title,
  content,
  mediaList,
  likeCount,
  dislikeCount,
  commentCount,
  hashtags,
}: FeedCardArticleProps) {
  return (
    <>
      <article className="p-[1rem]">
        <FeedHeader
          {...{ feedUuid, memberUuid, createdAt, updatedAt, title }}
        />
        <FeedContent {...{ content }} />
        <FeedGallery {...{ mediaList }} />
        <FeedButtonGroup
          {...{ feedUuid, likeCount, dislikeCount, commentCount }}
        />
        <FeedFooter {...{ hashtags }} />
      </article>
      <Separator className="bg-[#EEE] h-[0.5rem]" />
    </>
  );
}

import { Separator } from "@repo/ui/shadcn/separator";
import type { Feed } from "@/types/feed/feed-read-service";
import { FeedHeader } from "../molecules/FeedHeader";
import { FeedBody } from "../molecules/FeedBody";
import { FeedGallery } from "../molecules/FeedGallery";
import { FeedButtonGroup } from "../molecules/FeedButtonGroup";
import { FeedFooter } from "../molecules/FeedFooter";

interface FeedCardArticleProps extends Feed {
  link?: boolean;
}

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
  link,
}: FeedCardArticleProps) {
  return (
    <>
      <article className="p-[1rem]">
        <FeedHeader
          {...{ feedUuid, memberUuid, createdAt, updatedAt, title, link }}
        />
        <FeedBody {...{ feedUuid, content, link }} />
        <FeedGallery {...{ feedUuid, mediaList, link }} />
        <FeedButtonGroup
          {...{ feedUuid, likeCount, dislikeCount, commentCount }}
        />
        <FeedFooter {...{ hashtags }} />
      </article>
      <Separator className="bg-[#EEE] h-[0.5rem]" />
    </>
  );
}

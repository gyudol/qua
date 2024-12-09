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
  categoryName,
  hashtags,
  link,
}: FeedCardArticleProps) {
  return (
    <article className="px-[1rem] py-[1.5rem] flex flex-col space-y-3">
      <FeedHeader
        {...{
          feedUuid,
          memberUuid,
          createdAt,
          updatedAt,
          link,
        }}
      />
      <FeedGallery {...{ feedUuid, mediaList, link }} />
      <FeedBody {...{ feedUuid, content, link, title, categoryName }} />
      <FeedButtonGroup
        {...{ feedUuid, likeCount, dislikeCount, commentCount }}
      />
      <FeedFooter {...{ categoryName, hashtags }} />
    </article>
  );
}

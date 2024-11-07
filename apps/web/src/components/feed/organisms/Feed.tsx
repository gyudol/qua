import type { Feed as FeedType } from "@/types/contents/feed";
import FeedHeader from "../molecules/FeedHeader";
import {
  FeedButtonGroup,
  FeedContentWithLink,
  FeedMediaContainerWithLink,
} from "../molecules";
import FeedHashtagList from "../molecules/FeedHashtagList";

interface FeedProps extends FeedType {
  detail?: boolean;
}

export default function Feed({
  feedUuid,
  author,
  createdAt,
  updatedAt,
  content,
  statistics,
  mediaList,
  detail,
  hashtags,
}: FeedProps) {
  return (
    <article className="flex flex-col gap-[20px] p-[30px_28px] bg-white">
      <FeedHeader {...{ ...author, feedUuid, createdAt, updatedAt }} />

      {content ? (
        <FeedContentWithLink
          href={`/feeds/${feedUuid}`}
          content={content}
          withoutLink={detail}
          detail={detail}
        />
      ) : null}

      {mediaList.length !== 0 && (
        <FeedMediaContainerWithLink
          href={`/feeds/${feedUuid}`}
          mediaList={mediaList}
          withoutLink={detail}
        />
      )}

      <FeedButtonGroup {...{ feedUuid, ...statistics }} />

      <FeedHashtagList {...{ hashtags }} />
    </article>
  );
}

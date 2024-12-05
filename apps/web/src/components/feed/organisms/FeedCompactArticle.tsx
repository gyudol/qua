import { Separator } from "@repo/ui/shadcn/separator";
import Link from "next/link";
import type { Feed } from "@/types/feed/feed-read-service";
import { FeedButtonGroup } from "../molecules/FeedButtonGroup";
import { FeedFooter } from "../molecules/FeedFooter";
import { FeedCompactThumbnail } from "../molecules/FeedCompactThumbnail";
import { FeedCompactHeader } from "../molecules/FeedCompactHeader";

interface FeedCompactArticleProps extends Feed {
  link?: boolean;
}

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
  link,
}: FeedCompactArticleProps) {
  return (
    <>
      <article className="p-[1rem] flex gap-[1rem]">
        <Link href={`/feeds/${feedUuid}`}>
          <FeedCompactThumbnail {...{ mediaList }} />
        </Link>

        <div className="flex-1 flex flex-col">
          <FeedCompactHeader
            {...{ feedUuid, memberUuid, createdAt, updatedAt, title, link }}
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

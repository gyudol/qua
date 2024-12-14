import type { Feed } from "@/types/feed/feed-read-service";
import { FeedContent } from "../atoms/FeedContent";
import { FeedTitle } from "../atoms/FeedTitle";

interface FeedContentProps
  extends Pick<Feed, "feedUuid" | "content" | "title" | "categoryName"> {
  link?: boolean;
}

export function FeedBody({
  feedUuid,
  content,
  link,
  title,
  categoryName,
}: FeedContentProps) {
  return (
    <div className="flex flex-col gap-2 my-[0.5rem]">
      <FeedTitle {...{ feedUuid, title, categoryName, link }} />
      <FeedContent {...{ feedUuid, content, link }} />
    </div>
  );
}

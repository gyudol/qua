import type { Feed } from "@/types/feed/feed-read-service";
import { FeedContent } from "../atoms/FeedContent";

interface FeedContentProps extends Pick<Feed, "feedUuid" | "content"> {
  link?: boolean;
}

export function FeedBody({ feedUuid, content, link }: FeedContentProps) {
  return (
    <div className="my-[0.5rem]">
      <FeedContent {...{ feedUuid, content, link }} />
    </div>
  );
}

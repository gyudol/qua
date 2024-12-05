import type { Feed } from "@/types/feed/feed-read-service";
import { FeedHashtag } from "../atoms/FeedHashtag";

type FeedFooterProps = Pick<Feed, "hashtags">;

export function FeedFooter({ hashtags }: FeedFooterProps) {
  return (
    <footer className="py-[0.5rem]">
      <ul className="flex flex-wrap gap-[0.5rem]">
        {hashtags.map((hashtag) => (
          <li key={hashtag.name}>
            <FeedHashtag {...{ ...hashtag }} />
          </li>
        ))}
      </ul>
    </footer>
  );
}

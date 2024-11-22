import { HashtagWithLink } from "@/components/common/atoms";
import type { Hashtag as HashtagType } from "@/types/contents";

interface FeedHashtagListProp {
  hashtags: HashtagType[];
}

export default function FeedHashtagList({ hashtags }: FeedHashtagListProp) {
  return (
    <div className="flex gap-[8px_20px] flex-wrap">
      {hashtags.map((hashtag) => (
        <HashtagWithLink
          key={hashtag.name}
          {...{ href: `/search?hashtag=#${hashtag.name}`, hashtag }}
        />
      ))}
    </div>
  );
}

import { withLink } from "@/components/common/atoms";
import type { Hashtag as HashtagType } from "@/types/contents";

interface HashtagProp {
  className?: string;
  hashtag: HashtagType;
}

function Hashtag({ className, hashtag }: HashtagProp) {
  return <div {...{ className }}>#{hashtag.name}</div>;
}

const HashtagWithLink = withLink(Hashtag);

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

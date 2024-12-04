import { HashtagWithLink } from "@/components/common/atoms";
import type { Hashtag as HashtagType } from "@/types/contents";

interface FeedHashtagListProp {
  hashtags: HashtagType[];
}

export default function FeedHashtagList({ hashtags }: FeedHashtagListProp) {
  return (
    <ul className="flex gap-[0.5rem] flex-wrap">
      {hashtags.map((hashtag) => (
        <HashtagWithLink
          key={hashtag.name}
          className="text-emerald-500 text-xs font-bold bg-slate-50 rounded-xl py-1 px-2"
          {...{ href: `/search?hashtag=#${hashtag.name}`, hashtag }}
        />
      ))}
    </ul>
  );
}

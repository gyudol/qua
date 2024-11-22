import type { Hashtag as HashtagType } from "@/types/contents";
import withLink from "./withLink";

interface HashtagProp {
  className?: string;
  hashtag: HashtagType;
}

export function Hashtag({ className, hashtag }: HashtagProp) {
  return <span {...{ className }}>#{hashtag.name} </span>;
}

export const HashtagWithLink = withLink(Hashtag);

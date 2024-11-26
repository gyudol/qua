import type { Hashtag as HashtagType } from '@/types/contents';
import withLink from './withLink';

interface HashtagProp {
  className?: string;
  hashtag: HashtagType;
}

export function Hashtag({ className, hashtag }: HashtagProp) {
  return <li {...{ className }}># {hashtag.name} </li>;
}

export const HashtagWithLink = withLink(Hashtag);

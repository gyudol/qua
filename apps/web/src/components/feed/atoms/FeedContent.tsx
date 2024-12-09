import Link from 'next/link';
import type { PropsWithChildren } from 'react';
import type { Feed } from '@/types/feed/feed-read-service';

interface FeedContentProps extends Pick<Feed, 'feedUuid' | 'content'> {
  link?: boolean;
}

export function FeedContent({ feedUuid, content, link }: FeedContentProps) {
  if (link)
    return (
      <Link href={`/feeds/${feedUuid}`}>
        <Content className="line-clamp-3">{content}</Content>
      </Link>
    );
  return <Content>{content}</Content>;
}

interface ContentProps extends PropsWithChildren {
  className?: string;
}

function Content({ children, className }: ContentProps) {
  return (
    <p className={`text-gray-600 text-sm px-1 ${className}`}>{children}</p>
  );
}

import Link from 'next/link';
import type { PropsWithChildren } from 'react';
import type { Feed } from '@/types/feed/feed-read-service';

interface FeedTitleProps
  extends Pick<Feed, 'feedUuid' | 'title' | 'categoryName'> {
  link?: boolean;
}

export function FeedTitle({
  feedUuid,
  title,
  categoryName,
  link,
}: FeedTitleProps) {
  return (
    <div className="flex text-md font-bold items-center mb-2">
      <Link href={`/category/${categoryName}`}>
        <p className="text-white text-xs font-normal bg-teal-500 rounded-full py-1 px-2 mr-[0.5rem]">
          📚{categoryName}
        </p>
      </Link>
      <Title {...{ feedUuid, link }}>
        <h2 className="overflow-hidden">{title}</h2>
      </Title>
    </div>
  );
}

interface TitleProps extends Pick<Feed, 'feedUuid'>, PropsWithChildren {
  link?: boolean;
}

function Title({ feedUuid, children, link }: TitleProps) {
  if (link) return <Link href={`/feeds/${feedUuid}`}>{children}</Link>;
  return children;
}

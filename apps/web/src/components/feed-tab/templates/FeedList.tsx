'use client';

import { useQuery } from '@tanstack/react-query';
import { getAllFeed } from '@/actions/feed';
import Feed from '@/components/feed/organisms/Feed';
import FeedSortTap from '../organisms/FeedSortTap';

export default function FeedList() {
  const { data } = useQuery({
    queryKey: ['feeds'],
    queryFn: async () => getAllFeed(),
  });
  return (
    <div className="relative pt-[5rem]">
      <FeedSortTap />
      <section className="flex flex-col gap-2 pb-16 md:pb-16 md:pt-0">
        {data?.content.map((feed) => <Feed key={feed.feedUuid} {...feed} />)}
      </section>
    </div>
  );
}

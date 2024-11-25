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
    <>
      <FeedSortTap />
      <section className="flex flex-col gap-2 pb-16">
        {data?.content.map((feed) => <Feed key={feed.feedUuid} {...feed} />)}
      </section>
    </>
  );
}

'use client';

import { useQuery } from '@tanstack/react-query';
import { cn } from '@repo/ui/lib/utils';
import { Poppins } from 'next/font/google';
import { getAllFeed } from '@/actions/feed';
import Feed from '@/components/feed/organisms/Feed';
import { Group, List } from '@/components/common/icons';
import { alertNotImplemented } from '@/functions/utils';

const poppins = Poppins({ weight: '400', subsets: ['latin'] });

export default function FeedList() {
  const { data } = useQuery({
    queryKey: ['feeds'],
    queryFn: async () => getAllFeed(),
  });
  return (
    <section className="w-full pt-[6rem]">
      <div
        className={cn(
          'sticky top-0',
          'w-full flex justify-between items-center',
          'p-5 mb-2',
          'bg-white'
        )}
        style={{ zIndex: 10, display: 'sticky', top: 0 }}
      >
        <button
          type="button"
          className={cn(poppins.className, 'text-[var(--theme-color)]')}
          onClick={() => alertNotImplemented()}
        >
          Sorting by {'>'}
        </button>
        <div className="flex items-center gap-5">
          <button type="button" onClick={() => alertNotImplemented()}>
            <List />
          </button>
          <button type="button" onClick={() => alertNotImplemented()}>
            <Group />
          </button>
        </div>
      </div>
      <div className="flex flex-col gap-2 pb-16">
        {data?.content.map((feed) => <Feed key={feed.feedUuid} {...feed} />)}
      </div>
    </section>
  );
}

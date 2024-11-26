import { CommonLayout } from '@/components/common/molecules';
import FeedList from '@/components/feed-tab/templates/FeedList';
import { Suspense } from 'react';

export default function Page(): JSX.Element {
  return (
    <CommonLayout.Contents className="bg-[#EEE]">
      <Suspense fallback={<div>Loading...</div>}>
        <FeedList />
      </Suspense>
    </CommonLayout.Contents>
  );
}

import { CommonLayout } from '@/components/common/molecules';
import FeedWriteFrom from '@/components/feedform/organisms/FeedWriteFrom';

function page() {
  return (
    <CommonLayout.Contents>
      <FeedWriteFrom />
    </CommonLayout.Contents>
  );
}

export default page;

import { Suspense } from "react";
import { CommonLayout } from "@/components/common/molecules";
import FeedList from "@/components/feed-tab/templates/FeedList";

export default function Page(): JSX.Element {
  return (
    <CommonLayout.Contents>
      <Suspense fallback={<div>Loading...</div>}>
        <FeedList />
      </Suspense>
    </CommonLayout.Contents>
  );
}

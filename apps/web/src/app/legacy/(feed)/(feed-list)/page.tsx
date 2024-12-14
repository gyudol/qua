import { CommonLayout } from "@/components/common/atoms";
import { FeedPageFeedListSection } from "@/components/feed/pages/FeedPageFeedListSection";
import type { FeedViewType } from "@/types/feed/common";
import type { GetFeedsReq } from "@/types/feed/feed-read-service";

interface MainPageProps {
  searchParams: {
    sortBy: GetFeedsReq["sortBy"];
    view: FeedViewType;
  };
}

export default function MainPage({
  searchParams: { sortBy: _sortBy, view: _view },
}: MainPageProps) {
  const sortBy = _sortBy === "likes" ? "likes" : "latest";
  const view = _view === "compact" ? "compact" : "card";
  return (
    <CommonLayout.Contents className="bg-white">
      <FeedPageFeedListSection
        {...{
          sortBy,
          view,
        }}
      />
    </CommonLayout.Contents>
  );
}

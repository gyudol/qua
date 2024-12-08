import { CommonLayout } from "@/components/common/atoms";
import FeedListSection from "@/components/feed/templates/FeedListSection";
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
      <FeedListSection
        {...{
          sortBy,
          view,
        }}
      />
    </CommonLayout.Contents>
  );
}

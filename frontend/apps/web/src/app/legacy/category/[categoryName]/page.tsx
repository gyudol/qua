import { CommonLayout } from "@/components/common/atoms";
import { FeedPageFeedListSection } from "@/components/feed/pages/FeedPageFeedListSection";
import type { FeedViewType } from "@/types/feed/common";
import type { GetFeedsReq } from "@/types/feed/feed-read-service";

interface CategoryPageProps {
  params: {
    categoryName: string;
  };
  searchParams: {
    sortBy: GetFeedsReq["sortBy"];
    view: FeedViewType;
  };
}

export default function CategoryPage({
  params: { categoryName },
  searchParams: { sortBy: _sortBy, view: _view },
}: CategoryPageProps) {
  const sortBy = _sortBy === "likes" ? "likes" : "latest";
  const view = _view === "compact" ? "compact" : "card";
  return (
    <CommonLayout.Contents className="bg-white">
      <FeedPageFeedListSection
        {...{
          categoryName: decodeURI(categoryName),
          sortBy,
          view,
        }}
      />
    </CommonLayout.Contents>
  );
}

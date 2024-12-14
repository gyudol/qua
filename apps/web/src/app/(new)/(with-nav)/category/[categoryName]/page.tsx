import PageContainer from "@/components/@new/layouts/containers/PageContainer";
import { FeedPageFeedListSection } from "@/components/feed/pages/FeedPageFeedListSection";
import type { FeedViewType } from "@/types/feed/common";
import type { GetFeedsReq } from "@/types/feed/feed-read-service";

export default function CategoryPage({
  params: { categoryName },
  searchParams: { sortBy: _sortBy, view: _view },
}: {
  params: {
    categoryName: string;
  };
  searchParams: {
    sortBy: GetFeedsReq["sortBy"];
    view: FeedViewType;
  };
}) {
  const sortBy = _sortBy === "likes" ? "likes" : "latest";
  const view = _view === "compact" ? "compact" : "card";
  return (
    <PageContainer>
      <FeedPageFeedListSection
        {...{
          categoryName: decodeURI(categoryName),
          sortBy,
          view,
        }}
      />
    </PageContainer>
  );
}

import ContestSwiper from "@/components/@new/contest/ContestSwiper";
import PageContainer from "@/components/@new/layouts/containers/PageContainer";
import { FeedPageFeedListSection } from "@/components/feed/pages/FeedPageFeedListSection";
import type { FeedViewType } from "@/types/feed/common";
import type { GetFeedsReq } from "@/types/feed/feed-read-service";

export default function page({
  searchParams: { sortBy: _sortBy, view: _view },
}: {
  searchParams: {
    sortBy: GetFeedsReq["sortBy"];
    view: FeedViewType;
  };
}) {
  const sortBy = _sortBy === "likes" ? "likes" : "latest";
  const view = _view === "compact" ? "compact" : "card";
  return (
    <PageContainer>
      <ContestSwiper />
      <FeedPageFeedListSection {...{ sortBy, view }} />
    </PageContainer>
  );
}

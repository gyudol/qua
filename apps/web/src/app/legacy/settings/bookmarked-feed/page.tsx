import { CommonLayout } from "@/components/common/atoms";
import { BookmarkedFeedListSection } from "@/components/feed/pages/BookmarkedFeedListSection";
import type { FeedViewType } from "@/types/feed/common";

interface MainPageProps {
  searchParams: {
    view: FeedViewType;
  };
}

export default function MainPage({
  searchParams: { view: _view },
}: MainPageProps) {
  const view = _view === "compact" ? "compact" : "card";
  return (
    <CommonLayout.Contents className="bg-white">
      <BookmarkedFeedListSection
        {...{
          view,
        }}
      />
    </CommonLayout.Contents>
  );
}

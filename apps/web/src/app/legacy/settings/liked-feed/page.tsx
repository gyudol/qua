import { CommonLayout } from "@/components/common/atoms";
import { LikedFeedListSection } from "@/components/feed/pages/LikedFeedListSection";
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
      <LikedFeedListSection
        {...{
          view,
        }}
      />
    </CommonLayout.Contents>
  );
}

import { CommonLayout } from "@/components/common/atoms";
import FeedListSection from "@/components/feed/templates/FeedListSection";
import type { FeedViewType } from "@/types/feed/common";
import type { GetFeedsReq } from "@/types/feed/feed-read-service";

interface SearchPageProps {
  searchParams: {
    keyword: string;
    tag: string;
    sortBy: GetFeedsReq["sortBy"];
    view: FeedViewType;
  };
}

export default function SearchPage({
  searchParams: { keyword: _, tag, sortBy: _sortBy, view: _view },
}: SearchPageProps) {
  const sortBy = _sortBy === "likes" ? "likes" : "latest";
  const view = _view === "card" ? "card" : "compact";

  return (
    <CommonLayout.Contents className="bg-white">
      <FeedListSection
        {...{
          hashtagName: decodeURI(tag),
          sortBy,
          view,
        }}
      />
    </CommonLayout.Contents>
  );
}

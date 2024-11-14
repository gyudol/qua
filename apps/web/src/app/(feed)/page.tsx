import { CommonLayout } from "@/components/common/molecules";
import FeedList from "@/components/feed-tab/templates/FeedList";

export default function Page(): JSX.Element {
  return (
    <CommonLayout.Contents>
      <FeedList />
    </CommonLayout.Contents>
  );
}

import { CommonLayout } from "@/components/common/molecules";
import FeedListSection from "@/components/feed/templates/FeedListSection";

export default function Page(): JSX.Element {
  return (
    <CommonLayout.Contents>
      <FeedListSection />
    </CommonLayout.Contents>
  );
}

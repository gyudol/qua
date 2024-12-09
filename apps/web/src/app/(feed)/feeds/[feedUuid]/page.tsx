import { CommonLayout } from "@/components/common/molecules";
import { CommentSection } from "@/components/feed-comment-section/templates";
import { FeedDetailSection } from "@/components/feed/templates/FeedDetailSection";

interface PageProps {
  params: {
    feedUuid: string;
  };
}

export default function page({ params: { feedUuid } }: PageProps) {
  return (
    <CommonLayout.Contents className="flex flex-col bg-white">
      <FeedDetailSection {...{ feedUuid }} />
      <CommentSection {...{ feedUuid }} />
    </CommonLayout.Contents>
  );
}

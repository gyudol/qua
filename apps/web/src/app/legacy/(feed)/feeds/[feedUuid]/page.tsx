import { CommonLayout } from "@/components/common/molecules";
import CommentDrawer from "@/components/feed-comment-section/organisms/CommentDrawer";
import { FeedDetailSection } from "@/components/feed/templates/FeedDetailSection";

interface PageProps {
  params: {
    feedUuid: string;
  };
}

export default function page({ params: { feedUuid } }: PageProps) {
  return (
    <CommonLayout.Contents className="flex flex-col bg-white h-auto pt-[4rem]">
      <FeedDetailSection {...{ feedUuid }} />
      <CommentDrawer {...{ feedUuid }} />
    </CommonLayout.Contents>
  );
}

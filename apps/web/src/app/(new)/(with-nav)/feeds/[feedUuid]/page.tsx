import PageContainer from "@/components/@new/layouts/containers/PageContainer";
import CommentDrawer from "@/components/feed-comment-section/organisms/CommentDrawer";
import { FeedDetailSection } from "@/components/feed/templates/FeedDetailSection";

interface PageProps {
  params: {
    feedUuid: string;
  };
}

export default function page({ params: { feedUuid } }: PageProps) {
  return (
    <PageContainer>
      <FeedDetailSection {...{ feedUuid }} />
      <CommentDrawer {...{ feedUuid }} />
    </PageContainer>
  );
}

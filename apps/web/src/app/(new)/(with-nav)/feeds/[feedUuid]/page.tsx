import PageContainer from "@/components/@new/layouts/containers/PageContainer";
import { FeedDetailSection } from "@/components/feed/templates/FeedDetailSection";
import CommentDrawer from "@/components/feed-comment-section/organisms/CommentDrawer";

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

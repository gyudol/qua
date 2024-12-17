import PageContainer from "@/components/@new/layouts/containers/PageContainer";
import { FeedDetailSection } from "@/components/feed/templates/FeedDetailSection";
import CommentDrawer from "@/components/feed-comment-section/organisms/CommentDrawer";
import { FeedCommentDrawerContextProvider } from "@/provider/FeedCommnetDrawerContextProvider";

interface PageProps {
  params: {
    feedUuid: string;
  };
}

export default function page({ params: { feedUuid } }: PageProps) {
  return (
    <FeedCommentDrawerContextProvider>
      <PageContainer>
        <FeedDetailSection {...{ feedUuid }} />
      </PageContainer>
      <CommentDrawer {...{ feedUuid }} />
    </FeedCommentDrawerContextProvider>
  );
}

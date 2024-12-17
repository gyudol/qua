import { Separator } from "@repo/ui/shadcn/separator";
import PageContainer from "@/components/@new/layouts/containers/PageContainer";
import { CommentSection } from "@/components/feed-comment-section/templates";
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
      {/* <Separator className="bg-zinc-200 h-2" /> */}
      {/* <CommentSection {...{ feedUuid }} />
       */}
      <CommentDrawer {...{ feedUuid }} />
    </PageContainer>
  );
}

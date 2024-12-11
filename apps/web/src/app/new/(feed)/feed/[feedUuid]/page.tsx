import CommentDrawer from "@/components/feed-comment-section/organisms/CommentDrawer";
import { FeedDetailSection } from "@/components/feed/templates/FeedDetailSection";

interface PageProps {
  params: {
    feedUuid: string;
  };
}

export default function page({ params: { feedUuid } }: PageProps) {
  return (
    <main className="size-full overflow-scroll">
      <FeedDetailSection {...{ feedUuid }} />
      <CommentDrawer {...{ feedUuid }} />
    </main>
  );
}

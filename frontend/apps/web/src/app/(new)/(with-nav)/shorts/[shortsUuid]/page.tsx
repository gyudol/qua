import { getShorts } from "@/actions/shorts-read-service";
import PageContainer from "@/components/@new/layouts/containers/PageContainer";
import ShortsCommentDrawer from "@/components/shorts/organisms/ShortsCommentDrawer";
import { ShortsSlideContent } from "@/components/shorts/organisms/ShortsSlideContent";
import { CommentDrawerContextProvider } from "@/provider/CommentDrawerContextProvider";

export default async function page({
  params: { shortsUuid },
}: {
  params: {
    shortsUuid: string;
  };
}) {
  const shorts = await getShorts({ shortsUuid });
  return (
    <PageContainer>
      <CommentDrawerContextProvider
        defaultValue={{ targetType: "shorts", targetUuid: shortsUuid }}
      >
        <div className="relative size-full">
          <ShortsSlideContent {...shorts} isActive />
        </div>
        <ShortsCommentDrawer />
      </CommentDrawerContextProvider>
    </PageContainer>
  );
}

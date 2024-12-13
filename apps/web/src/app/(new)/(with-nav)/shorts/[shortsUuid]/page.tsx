import { getShorts } from "@/actions/shorts-read-service";
import PageContainer from "@/components/@new/layouts/containers/PageContainer";
import { ShortsSlideContent } from "@/components/shorts/organisms/ShortsSlideContent";

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
      <div className="relative size-full">
        <ShortsSlideContent {...shorts} isActive />
      </div>
    </PageContainer>
  );
}

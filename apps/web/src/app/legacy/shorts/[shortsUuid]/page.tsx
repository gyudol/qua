import { getShorts } from "@/actions/shorts-read-service";
import { CommonLayout } from "@/components/common/atoms";
import { ShortsSlideContent } from "@/components/shorts/organisms/ShortsSlideContent";

interface ShortsDetailPage {
  params: {
    shortsUuid: string;
  };
}

export default async function page({
  params: { shortsUuid },
}: ShortsDetailPage) {
  const shorts = await getShorts({ shortsUuid });
  return (
    <CommonLayout.Contents className="w-full h-full">
      <ShortsSlideContent {...shorts} isActive />
    </CommonLayout.Contents>
  );
}

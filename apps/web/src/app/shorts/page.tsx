import { CommonLayout } from "@/components/common/atoms";
import { ShortsSection } from "@/components/shorts/templates";

export default function page() {
  return (
    <CommonLayout.Contents className="w-full h-full">
      <ShortsSection />
    </CommonLayout.Contents>
  );
}

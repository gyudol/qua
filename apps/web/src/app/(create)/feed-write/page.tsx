import { CommonLayout } from "@/components/common/molecules";
import FeedWriteFrom from "@/components/feedform/organisms/FeedWriteFrom";

function page() {
  return (
    <CommonLayout.Contents className="w-full h-auto px-[1rem] md:px-[2rem] py-[2rem] bg-[#FDFCFC]">
      <FeedWriteFrom />
    </CommonLayout.Contents>
  );
}

export default page;

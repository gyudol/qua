import { CommonLayout } from "@/components/common/molecules";
import ShortsWriteForm from "@/components/shortsform/organisms/ShortsWriteForm";

function page() {
  return (
    <CommonLayout.Contents className="w-full h-auto px-[1rem] md:px-[2rem] py-[2rem] bg-[#FDFCFC] transition-all">
      <ShortsWriteForm />
    </CommonLayout.Contents>
  );
}

export default page;

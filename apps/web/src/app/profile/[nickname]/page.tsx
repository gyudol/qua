import { CommonLayout } from "@/components/common/atoms";
import { ProfileCardSection } from "@/components/profile/templates";

interface PageProps {
  params: {
    nickname: string;
  };
}

export default function page({ params: { nickname } }: PageProps) {
  return (
    <CommonLayout.Contents className="bg-[#EEE] flex flex-col gap-[20px] pb-32">
      <ProfileCardSection {...{ nickname: decodeURI(nickname) }} />
    </CommonLayout.Contents>
  );
}

import { CommonLayout } from "@/components/common/molecules";
import Feed from "@/components/feed/organisms/Feed";

export default function Page(): JSX.Element {
  return (
    <CommonLayout.Contents>
      <Feed />
    </CommonLayout.Contents>
  );
}

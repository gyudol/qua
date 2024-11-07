import { getFeed } from "@/actions/feed";
import { CommonLayout } from "@/components/common/molecules";
import Feed from "@/components/feed/organisms/Feed";

interface PageProps {
  params: {
    feedUuid: string;
  };
}

export default async function page({ params }: PageProps) {
  const feed = await getFeed(params.feedUuid);
  return (
    <CommonLayout.Contents>
      <Feed {...feed} detail />
    </CommonLayout.Contents>
  );
}

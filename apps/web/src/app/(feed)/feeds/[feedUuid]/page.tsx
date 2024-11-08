// import { getFeed } from "@/actions/feed";
import { CommonLayout } from "@/components/common/molecules";
// import Feed from "@/components/feed/organisms/Feed";

interface PageProps {
  params: {
    feedUuid: string;
  };
}

export default function page({ params }: PageProps) {
  // const feed = await getFeed(params.feedUuid);
  return (
    <CommonLayout.Contents>
      {params.feedUuid}
      {/* <Feed {...feed} detail /> */}
    </CommonLayout.Contents>
  );
}

import { getFeed } from "@/actions/feed";
// import { CommentList } from "@/components/@legacy-comments/templates";
import { CommonLayout } from "@/components/common/molecules";
import { CommentSection } from "@/components/feed-comment-section/templates";
import Feed from "@/components/feed/organisms/Feed";

interface PageProps {
  params: {
    feedUuid: string;
  };
}

export default async function page({ params }: PageProps) {
  const feed = await getFeed(params.feedUuid);
  return (
    <CommonLayout.Contents className="bg-[#EEE] flex flex-col gap-[20px] pb-32">
      <Feed {...feed} detail />
      {/* <CommentList
        targetType="feeds"
        isRecomment={false}
        feedUuid={params.feedUuid}
      /> */}
      <CommentSection {...{ feedUuid: params.feedUuid }} />
    </CommonLayout.Contents>
  );
}

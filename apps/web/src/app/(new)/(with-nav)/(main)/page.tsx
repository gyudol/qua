import { getServerSession } from "next-auth";
import { redirect } from "next/navigation";
import { options } from "@/app/api/auth/[...nextauth]/authOption";
import ContestSwiper from "@/components/@new/contest/ContestSwiper";
import PageContainer from "@/components/@new/layouts/containers/PageContainer";
import { FeedPageFeedListSection } from "@/components/feed/pages/FeedPageFeedListSection";
import type { FeedViewType } from "@/types/feed/common";
import { FeedRecommendsListSection } from "@/components/feed/pages/FeedRecommendsListSection";

export default async function page({
  searchParams: { sortBy: _sortBy, view: _view },
}: {
  searchParams: {
    sortBy?: "latest" | "likes" | "recommends";
    view?: FeedViewType;
  };
}) {
  const session = await getServerSession(options);
  const { memberUuid } = (
    session?.user ? session.user : { memberUuid: undefined }
  ) as { memberUuid?: string };

  let sortBy: "latest" | "likes" | "recommends";

  if (_sortBy === "likes") sortBy = "likes";
  else if (_sortBy === "latest") sortBy = "latest";
  else sortBy = "recommends";

  const view = _view === "compact" ? "compact" : "card";

  if (sortBy === "recommends") {
    if (!memberUuid) return redirect("?latest");
    return (
      <PageContainer>
        <ContestSwiper />
        <FeedRecommendsListSection {...{ memberUuid, view }} />
      </PageContainer>
    );
  }

  return (
    <PageContainer>
      <ContestSwiper />
      <FeedPageFeedListSection {...{ sortBy, view }} />
    </PageContainer>
  );
}

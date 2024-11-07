import { getAllFeed } from "@/actions/feed";
import Feed from "@/components/feed/organisms/Feed";

export default async function FeedList() {
  const { content } = await getAllFeed();
  return (
    <section className="flex flex-col gap-[10px] pb-[80px] bg-[#DDD]">
      {content.map((feed) => (
        <Feed key={feed.feedUuid} {...feed} />
      ))}
    </section>
  );
}

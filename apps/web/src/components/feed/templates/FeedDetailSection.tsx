import { getFeed } from "@/actions/feed-read-service";
import type { Feed } from "@/types/feed/feed-read-service";
import { FeedCardArticle } from "../organisms/FeedCardArticle";

type FeedSectionProps = Pick<Feed, "feedUuid">;

export async function FeedDetailSection({ feedUuid }: FeedSectionProps) {
  const feed = await getFeed({ feedUuid });
  return (
    <section>
      <FeedCardArticle {...feed} />
    </section>
  );
}

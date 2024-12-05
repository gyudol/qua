import type { Feed } from "@/types/feed/feed-read-service";

type FeedContentProps = Pick<Feed, "content">;

export function FeedContent({ content }: FeedContentProps) {
  return (
    <div className="my-[0.5rem]">
      <p className="text-gray-600">{content}</p>
    </div>
  );
}

import { Bookmark } from "lucide-react";
import { ButtonWithAuth } from "@/components/common/atoms";
import {
  useFeedBookmarkMutation,
  useFeedBookmarkStatusQuery,
} from "@/hooks/utility-service/bookmark-service";
import type { Feed } from "@/types/feed/feed-read-service";

type FeedBookmarkButtonProps = Pick<Feed, "feedUuid">;

export function FeedBookmarkButton({ feedUuid }: FeedBookmarkButtonProps) {
  const { data } = useFeedBookmarkStatusQuery({ feedUuid });
  const { mutate } = useFeedBookmarkMutation({ feedUuid });

  return (
    <ButtonWithAuth
      type="button"
      onClick={() => mutate(!data)}
      className="flex gap-[0.5rem] items-center"
    >
      <span>
        <Bookmark
          className="m-[0.25rem]"
          size="1.25rem"
          color="#B1B1B1"
          fill={data ? "#B1B1B1" : "none"}
        />
      </span>
    </ButtonWithAuth>
  );
}

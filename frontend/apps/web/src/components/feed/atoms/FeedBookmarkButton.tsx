import { Bookmark } from 'lucide-react';
import { ButtonWithAuth } from '@/components/common/atoms';
import {
  useFeedBookmarkMutation,
  useFeedBookmarkStatusQuery,
} from '@/hooks/utility-service/bookmark-service';
import type { Feed } from '@/types/feed/feed-read-service';

type FeedBookmarkButtonProps = Pick<Feed, 'feedUuid'>;

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
          className="m-[0.25rem] text-slate-400"
          size="1.25rem"
          color="#48d0bf"
          fill={data ? '#48d0bf' : 'none'}
        />
      </span>
    </ButtonWithAuth>
  );
}

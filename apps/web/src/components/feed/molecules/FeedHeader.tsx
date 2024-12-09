'use client';

import Link from 'next/link';
import { PostedAt } from '@/components/common/atoms';
import { useMemberCompactProfile } from '@/hooks';
import type { Feed } from '@/types/feed/feed-read-service';
import { MemberProfileImage } from '@/components/profile/atoms/MemberProfileImage';
import { FeedMoreOption } from './FeedMoreOption';

interface FeedHeaderProps
  extends Pick<Feed, 'feedUuid' | 'memberUuid' | 'createdAt' | 'updatedAt'> {
  link?: boolean;
}

export function FeedHeader({
  feedUuid,
  memberUuid,
  createdAt,
  updatedAt,
  link,
}: FeedHeaderProps) {
  const { data: member, status } = useMemberCompactProfile({ memberUuid });
  if (status === 'pending') return <div className="h-10">loading</div>;
  if (status !== 'success') return null;
  const profileImageUrl = member.profileImageUrl;
  const nickname = member.nickname || memberUuid;

  return (
    <header className="flex flex-col mb-[0.25rem] h-[2.4rem]">
      <div className="flex justify-between items-center">
        <div className="flex items-center mb-[0.25rem]">
          <div className="mr-[1rem]">
            <MemberProfileImage
              {...{ size: '2.4rem', profileImageUrl, nickname }}
              link
            />
          </div>
          <div className="flex flex-col">
            <Link
              href={`/profile/${nickname}`}
              className="mr-[1rem] text-sm font-bold text-slate-600"
            >
              {nickname}
            </Link>
            <PostedAt {...{ createdAt, updatedAt }} />
          </div>
        </div>
        <div>
          <FeedMoreOption {...{ feedUuid, memberUuid }} />
        </div>
      </div>
    </header>
  );
}

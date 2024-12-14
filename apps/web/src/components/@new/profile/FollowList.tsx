"use client";

import type {
  InfiniteData,
  UseInfiniteQueryResult,
} from "@tanstack/react-query";
import { useInfiniteScroll, useMemberCompactProfile } from "@/hooks";
import {
  useFollowersInfiniteQuery,
  useFollowingsInfiniteQuery,
} from "@/hooks/utility-service/follow-service";
import type { Pagination } from "@/types/common";
import { MemberProfileImage } from "@/components/profile/atoms/MemberProfileImage";
import ProfileFollowButton from "./ProfileFollowButton";
import GradeMark from "./GradeMark";
import BadgeMark from "./BadgeMark";

export function FollowerListSection({ memberUuid }: { memberUuid: string }) {
  const useInfiniteQueryResult = useFollowersInfiniteQuery({ memberUuid });
  return <FollowListSection {...{ useInfiniteQueryResult }} />;
}

export function FollowingListSection({ memberUuid }: { memberUuid: string }) {
  const useInfiniteQueryResult = useFollowingsInfiniteQuery({ memberUuid });
  return <FollowListSection {...{ useInfiniteQueryResult }} />;
}

function FollowListSection({
  useInfiniteQueryResult: {
    data,
    hasNextPage,
    isFetchingNextPage,
    fetchNextPage,
  },
}: {
  useInfiniteQueryResult: UseInfiniteQueryResult<
    InfiniteData<Pagination<string>>
  >;
}) {
  const observerRef = useInfiniteScroll({
    hasNextPage,
    isFetchingNextPage,
    fetchNextPage,
  });
  return (
    <section className="p-[1rem]">
      <ul>
        {data?.pages[0].content.length
          ? data.pages.map((page) =>
              page.content.map((targetUuid) => (
                <FollowListItem key={targetUuid} {...{ targetUuid }} />
              )),
            )
          : null}
      </ul>
      <div ref={observerRef} />
    </section>
  );
}

function FollowListItem({ targetUuid }: { targetUuid: string }) {
  const { data, status } = useMemberCompactProfile({ memberUuid: targetUuid });
  if (status === "error") return null;
  if (status === "pending") return null;
  const { profileImageUrl, nickname, equippedBadgeId: badgeId, gradeId } = data;

  return (
    <li className="w-full py-[1rem] border-b">
      <div className="flex justify-between items-center">
        <div className="flex items-center gap-[1rem]">
          <div>
            <MemberProfileImage
              {...{ profileImageUrl, nickname, size: "3rem" }}
              link
            />
          </div>
          <div className="flex gap-1">
            <p className="text">{nickname}</p>
            <GradeMark {...{ gradeId, size: "1.5rem" }} />
            {badgeId ? <BadgeMark {...{ badgeId, size: "1.5rem" }} /> : null}
          </div>
        </div>
        <div className="w-[8rem] h-[2.5rem] flex">
          <ProfileFollowButton {...{ memberUuid: targetUuid }} />
        </div>
      </div>
    </li>
  );
}

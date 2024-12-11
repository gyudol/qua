"use client";

import { useInfiniteScroll } from "@/hooks";
import { useFollowersInfiniteQuery } from "@/hooks/utility-service/follow-service";
import type { GetFollowingsReq } from "@/types/utility-service";
import { MemberProfileListItem } from "../organisms/MemberProfileListItem";

type FollowerSectionProps = Pick<GetFollowingsReq, "memberUuid">;

export function FollowerSection({ memberUuid }: FollowerSectionProps) {
  const {
    data,
    status,
    error,
    hasNextPage,
    isFetchingNextPage,
    fetchNextPage,
  } = useFollowersInfiniteQuery({
    memberUuid,
  });
  const objectRef = useInfiniteScroll({
    hasNextPage,
    isFetchingNextPage,
    fetchNextPage,
  });

  if (status === "error") throw Error(error.message);
  if (status === "pending") return null;

  return (
    <section>
      {data.pages[0].content.length ? (
        data.pages.map((page) =>
          page.content.map((followingUuid) => (
            <MemberProfileListItem
              key={followingUuid}
              {...{ memberUuid: followingUuid }}
            />
          )),
        )
      ) : (
        <div>팔로잉이 없습니다.</div>
      )}
      <div ref={objectRef} />
    </section>
  );
}

"use client";

import { useInfiniteScroll } from "@/hooks";
import { useFollowingsInfiniteQuery } from "@/hooks/utility-service/follow-service";
import type { GetFollowingsReq } from "@/types/utility-service";
import { MemberProfileListItem } from "../organisms/MemberProfileListItem";

type FollowingSectionProps = Pick<GetFollowingsReq, "memberUuid">;

export function FollowingSection({ memberUuid }: FollowingSectionProps) {
  const {
    data,
    status,
    error,
    hasNextPage,
    isFetchingNextPage,
    fetchNextPage,
  } = useFollowingsInfiniteQuery({
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

"use client";

import type { MemberProfile } from "@/types/member/member-read-service";
import { useGetMemberShortsesInfiniteQuery } from "@/hooks/shorts-read-service";
import { useInfiniteScroll } from "@/hooks";
import { ContentsArticle } from "../molecules/ContentsArticle";

type ShortsTabProps = Pick<MemberProfile, "memberUuid">;

export function ShortsTab({ memberUuid }: ShortsTabProps) {
  const {
    data,
    status,
    error,
    hasNextPage,
    isFetchingNextPage,
    fetchNextPage,
  } = useGetMemberShortsesInfiniteQuery({
    memberUuid,
    pageSize: 9,
  });

  const objectRef = useInfiniteScroll({
    hasNextPage,
    isFetchingNextPage,
    fetchNextPage,
  });

  if (status === "error") throw Error(error.message);
  if (status === "pending")
    return (
      <section className="mt-4 grid grid-cols-3 gap-1">
        <ContentsArticle />
        <ContentsArticle />
        <ContentsArticle />
        <ContentsArticle />
        <ContentsArticle />
        <ContentsArticle />
        <ContentsArticle />
        <ContentsArticle />
        <ContentsArticle />
      </section>
    );

  return (
    <section className="mt-4 grid grid-cols-3 gap-1">
      {data.pages.map((page) =>
        page.content.map(({ shortsUuid, title, media }) => {
          return (
            <ContentsArticle
              key={shortsUuid}
              {...{ title, media, contentsUuid: shortsUuid }}
            />
          );
        }),
      )}
      <div ref={objectRef} />
    </section>
  );
}

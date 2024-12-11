"use client";

import Link from "next/link";
import type { MemberProfile } from "@/types/member/member-read-service";
import { useGetMemberFeedsInfiniteQuery, useInfiniteScroll } from "@/hooks";
import { ContentsArticle } from "../molecules/ContentsArticle";

type FeedTabProps = Pick<MemberProfile, "memberUuid">;

export function FeedTab({ memberUuid }: FeedTabProps) {
  const {
    data,
    status,
    error,
    hasNextPage,
    isFetchingNextPage,
    fetchNextPage,
  } = useGetMemberFeedsInfiniteQuery({
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
        page.content.map(({ feedUuid, title, mediaList }) => {
          let media;

          if (mediaList.length) {
            media = mediaList[0];
          }

          return (
            <Link key={feedUuid} href={`/feeds/${feedUuid}`}>
              <ContentsArticle {...{ title, media, contentsUuid: feedUuid }} />
            </Link>
          );
        }),
      )}
      <div ref={objectRef} />
    </section>
  );
}

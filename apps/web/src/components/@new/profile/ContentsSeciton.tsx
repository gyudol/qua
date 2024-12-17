"use client";

import { cn } from "@repo/ui/lib/utils";
import type { LucideProps } from "lucide-react";
import { Bookmark, Heart, List, PencilLine, Video } from "lucide-react";
import type { ForwardRefExoticComponent, RefAttributes } from "react";
import { useState } from "react";
import type {
  InfiniteData,
  UseInfiniteQueryResult,
} from "@tanstack/react-query";
import Image from "next/image";
import Link from "next/link";
import {
  useFeedBookmarksInfiniteQuery,
  useShortsBookmarksInfiniteQuery,
} from "@/hooks/utility-service/bookmark-service";
import {
  useGetFeedQuery,
  useGetLikesInfiniteQuery,
  useGetMemberFeedsInfiniteQuery,
  useInfiniteScroll,
} from "@/hooks";
import type { Feed } from "@/types/feed/feed-read-service";
import type { Pagination } from "@/types/common";
import { Skeleton } from "@/components/common/atoms";
import {
  useGetMemberShortsesInfiniteQuery,
  useGetShortsQuery,
} from "@/hooks/shorts-service";
import type { Shorts } from "@/types/shorts/shorts-read-service";

type Icon = ForwardRefExoticComponent<
  Omit<LucideProps, "ref"> & RefAttributes<SVGSVGElement>
>;

type TabContent1 = "post" | "like" | "bookmark";
type TabContent2 = "feed" | "shorts";

interface TabType1 {
  Icon: Icon;
  content: TabContent1;
}
interface TabType2 {
  Icon: Icon;
  content: TabContent2;
}

export default function ContentsSection({
  memberUuid,
  my,
}: {
  memberUuid: string;
  my?: boolean;
}) {
  const [tab1, setTab1] = useState<TabContent1>("post");
  const [tab2, setTab2] = useState<TabContent2>("feed");

  const tabList1: TabType1[] = [
    { Icon: PencilLine, content: "post" },
    { Icon: Heart, content: "like" },
    { Icon: Bookmark, content: "bookmark" },
  ];
  const tabList2: TabType2[] = [
    { Icon: List, content: "feed" },
    { Icon: Video, content: "shorts" },
  ];

  return (
    <>
      <div className="shadow-sm px-[1rem] pt-[1rem] mt-[1rem] pb-[1rem] flex flex-col gap-[1.25rem] bg-white">
        {my ? (
          <ul className="flex">
            {tabList1.map(({ Icon, content }) => (
              <li key={content} className="flex-1">
                <button
                  type="button"
                  className={cn(
                    "w-full p-1 flex justify-center",
                    "border-b-2",
                    tab1 === content ? "border-teal-400" : "border-zinc-300",
                  )}
                  onClick={() => setTab1(content)}
                >
                  <Icon
                    className={
                      tab1 === content ? "stroke-teal-400" : "stroke-zinc-300"
                    }
                  />
                </button>
              </li>
            ))}
          </ul>
        ) : null}
        <ul className="flex gap-[0.5rem] items-center">
          {tabList2.map(({ Icon, content }) => (
            <li key={content} className="flex-1">
              <button
                type="button"
                className={cn(
                  "w-full p-1 flex justify-center",
                  "flex-1 rounded-lg transition-all",
                  tab2 === content
                    ? "bg-teal-400 scale-100"
                    : "bg-zinc-200 scale-90",
                )}
                onClick={() => setTab2(content)}
              >
                <Icon className="stroke-white" />
              </button>
            </li>
          ))}
        </ul>
      </div>
      {tab1 === "post" && tab2 === "feed" ? (
        <PostFeedListSection {...{ memberUuid }} />
      ) : null}
      {tab1 === "like" && tab2 === "feed" ? <LikeFeedListSection /> : null}
      {tab1 === "bookmark" && tab2 === "feed" ? (
        <BookmarkFeedListSection />
      ) : null}
      {tab1 === "post" && tab2 === "shorts" ? (
        <PostShortsListSection {...{ memberUuid }} />
      ) : null}
      {tab1 === "like" && tab2 === "shorts" ? <LikeShortsListSection /> : null}
      {tab1 === "bookmark" && tab2 === "shorts" ? (
        <BookmarkShortsListSection />
      ) : null}
    </>
  );
}

function PostFeedListSection({ memberUuid }: { memberUuid: string }) {
  const useInfiniteQueryResult = useGetMemberFeedsInfiniteQuery({ memberUuid });
  return <FeedListSection {...{ useInfiniteQueryResult }} />;
}
function LikeFeedListSection() {
  const useInfiniteQueryResult = useGetLikesInfiniteQuery({ kind: "feed" });
  return <FeedListSection {...{ useInfiniteQueryResult }} />;
}
function BookmarkFeedListSection() {
  const useInfiniteQueryResult = useFeedBookmarksInfiniteQuery({});
  return <FeedListSection {...{ useInfiniteQueryResult }} />;
}
function FeedListSection({
  useInfiniteQueryResult: {
    data,
    hasNextPage,
    isFetchingNextPage,
    fetchNextPage,
  },
}: {
  useInfiniteQueryResult: UseInfiniteQueryResult<
    InfiniteData<Pagination<Feed | string>>
  >;
}) {
  const observerRef = useInfiniteScroll({
    hasNextPage,
    isFetchingNextPage,
    fetchNextPage,
  });
  return (
    <section className="px-[1rem] pb-[2rem]">
      <div className="grid grid-cols-3 gap-1">
        {data?.pages.length
          ? data.pages.map((page) =>
              page.content.map((value) => {
                if (typeof value === "string")
                  return (
                    <Link
                      key={value}
                      href={`/feeds/${value}`}
                      className="w-full aspect-square"
                    >
                      <article className="size-full">
                        <FeedThumbnailByUuid {...{ feedUuid: value }} />
                      </article>
                    </Link>
                  );
                return (
                  <Link
                    key={value.feedUuid}
                    href={`/feeds/${value.feedUuid}`}
                    className="w-full aspect-square"
                  >
                    <article className="size-full">
                      <FeedThumbnail {...{ ...value }} />
                    </article>
                  </Link>
                );
              }),
            )
          : null}
      </div>
      <div ref={observerRef} />
    </section>
  );
}

function FeedThumbnailByUuid({ feedUuid }: { feedUuid: string }) {
  const { data: feed, status } = useGetFeedQuery({ feedUuid });
  if (status === "error") return null;
  if (status === "pending")
    return <Skeleton className="w-full aspect-square" />;
  const { title, mediaList } = feed;
  let pic;
  if (mediaList.length) {
    const firstMedia = mediaList[0];
    pic =
      firstMedia.mediaType === "IMAGE"
        ? firstMedia.assets.IMAGE
        : firstMedia.assets.VIDEO_THUMBNAIL;
  }

  return pic ? (
    <figure className="relative size-full">
      <Image
        src={`https://media.qua.world/${pic.mediaUrl}`}
        alt={title}
        fill
        objectFit="cover"
      />
    </figure>
  ) : (
    <p className="size-full flex justify-center items-center bg-teal-400">
      <span className="text-md text-white font-bold break-keep m-[1rem] line-clamp-3">
        {title}
      </span>
    </p>
  );
}

function FeedThumbnail({ title, mediaList }: Feed) {
  let pic;
  if (mediaList.length) {
    const firstMedia = mediaList[0];
    pic =
      firstMedia.mediaType === "IMAGE"
        ? firstMedia.assets.IMAGE
        : firstMedia.assets.VIDEO_THUMBNAIL;
  }

  return pic ? (
    <figure className="relative size-full">
      <Image
        src={`https://media.qua.world/${pic.mediaUrl}`}
        alt={title}
        fill
        objectFit="cover"
      />
    </figure>
  ) : (
    <p className="size-full flex justify-center items-center bg-teal-400">
      <span className="text-md text-white font-bold break-keep m-[1rem] line-clamp-3">
        {title}
      </span>
    </p>
  );
}

function PostShortsListSection({ memberUuid }: { memberUuid: string }) {
  const useInfiniteQueryResult = useGetMemberShortsesInfiniteQuery({
    memberUuid,
  });
  return <ShortsListSection {...{ useInfiniteQueryResult }} />;
}
function LikeShortsListSection() {
  const useInfiniteQueryResult = useGetLikesInfiniteQuery({ kind: "shorts" });
  return <ShortsListSection {...{ useInfiniteQueryResult }} />;
}
function BookmarkShortsListSection() {
  const useInfiniteQueryResult = useShortsBookmarksInfiniteQuery({});
  return <ShortsListSection {...{ useInfiniteQueryResult }} />;
}
function ShortsListSection({
  useInfiniteQueryResult: {
    data,
    hasNextPage,
    isFetchingNextPage,
    fetchNextPage,
  },
}: {
  useInfiniteQueryResult: UseInfiniteQueryResult<
    InfiniteData<Pagination<Shorts | string>>
  >;
}) {
  const observerRef = useInfiniteScroll({
    hasNextPage,
    isFetchingNextPage,
    fetchNextPage,
  });
  return (
    <section className="px-[1rem] pb-[2rem]">
      <div className="grid grid-cols-3 gap-1">
        {data?.pages.length
          ? data.pages.map((page) =>
              page.content.map((value) => {
                if (typeof value === "string")
                  return (
                    <Link
                      key={value}
                      href={`/shorts/${value}`}
                      className="w-full aspect-[9/16]"
                    >
                      <article className="size-full">
                        <ShortsThumbnailByUuid {...{ shortsUuid: value }} />
                      </article>
                    </Link>
                  );
                return (
                  <Link
                    key={value.shortsUuid}
                    href={`/shorts/${value.shortsUuid}`}
                    className="w-full aspect-[9/16]"
                  >
                    <article className="size-full">
                      <ShortsThumbnail {...{ ...value }} />
                    </article>
                  </Link>
                );
              }),
            )
          : null}
      </div>
      <div ref={observerRef} />
    </section>
  );
}

function ShortsThumbnailByUuid({ shortsUuid }: { shortsUuid: string }) {
  const { data: shorts, status } = useGetShortsQuery({ shortsUuid });
  if (status === "error") return null;
  if (status === "pending")
    return <Skeleton className="w-full aspect-square" />;
  const { title, media } = shorts;
  const pic = media.assets.VIDEO_THUMBNAIL;

  return (
    <figure className="relative size-full">
      <Image
        src={`https://media.qua.world/${pic.mediaUrl}`}
        alt={title}
        fill
        objectFit="cover"
      />
    </figure>
  );
}

function ShortsThumbnail({ title, media }: Shorts) {
  const pic = media.assets.VIDEO_THUMBNAIL;

  return (
    <figure className="relative size-full">
      <Image
        src={`https://media.qua.world/${pic.mediaUrl}`}
        alt={title}
        fill
        objectFit="cover"
      />
    </figure>
  );
}

import Link from "next/link";
import type { PropsWithChildren } from "react";
import type { Feed } from "@/types/feed/feed-read-service";

interface FeedTitleProps
  extends Pick<Feed, "feedUuid" | "title" | "categoryName"> {
  link?: boolean;
}

export function FeedTitle({
  feedUuid,
  title,
  categoryName,
  link,
}: FeedTitleProps) {
  return (
    <h2 className="text-lg font-bold items-center">
      <Link href={`/category/${categoryName}`}>
        <span className="text-emerald-700 text-base font-bold bg-slate-50 rounded-xl py-1 px-2 mr-[0.5rem]">
          📚{categoryName}
        </span>
      </Link>
      <Title {...{ feedUuid, link }}>
        <span className="overflow-hidden">{title}</span>
      </Title>
    </h2>
  );
}

interface TitleProps extends Pick<Feed, "feedUuid">, PropsWithChildren {
  link?: boolean;
}

function Title({ feedUuid, children, link }: TitleProps) {
  if (link) return <Link href={`/feeds/${feedUuid}`}>{children}</Link>;
  return children;
}

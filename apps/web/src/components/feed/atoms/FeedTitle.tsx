import Link from "next/link";
import type { PropsWithChildren } from "react";
import type { Feed } from "@/types/feed/feed-read-service";

interface FeedTitleProps extends Pick<Feed, "feedUuid" | "title"> {
  link?: boolean;
}

export function FeedTitle({ feedUuid, title, link }: FeedTitleProps) {
  if (link)
    return (
      <Link href={`/feeds/${feedUuid}`}>
        <Title>{title}</Title>
      </Link>
    );

  return <Title>{title}</Title>;
}

function Title({ children }: PropsWithChildren) {
  return <h2 className="text-lg font-bold">{children}</h2>;
}

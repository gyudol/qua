import React from "react";
import DetailHeader from "@/components/@new/layouts/headers/DetailHeader";
import { getFeed } from "@/actions/feed-read-service";
import { FeedTitle } from "@/components/feed/atoms/FeedTitle";

export default async function layout({
  children,
  params: { feedUuid },
}: {
  children: React.ReactNode;
  params: { feedUuid: string };
}) {
  const { title, categoryName } = await getFeed({ feedUuid });
  return (
    <>
      <DetailHeader
        title={
          <div
          // className="text-black"
          >
            <FeedTitle {...{ feedUuid, title, categoryName }} />
          </div>
        }
      />
      {children}
    </>
  );
}

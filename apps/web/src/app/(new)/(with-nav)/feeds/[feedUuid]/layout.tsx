import React from "react";
import Link from "next/link";
import DetailHeader from "@/components/@new/layouts/headers/DetailHeader";
import { getFeed } from "@/actions/feed-read-service";

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
          <div className="flex text-md font-bold items-center">
            <Link href={`/category/${categoryName}`}>
              <p
                className="
        text-white text-xs font-normal 
        bg-teal-500 rounded-full 
          py-1 px-2 mr-[0.5rem]
          text-nowrap 
          "
              >
                📚{categoryName}
              </p>
            </Link>
            <h2 className="overflow-hidden break-keep line-clamp-1">{title}</h2>
          </div>
        }
      />
      {children}
    </>
  );
}

"use client";

import Link from "next/link";
import { Swiper, SwiperSlide } from "swiper/react";
import { Pagination } from "swiper/modules";
import type { Feed } from "@/types/feed/feed-read-service";
import { FeedGalleryItem } from "../atoms/FeedGalleryItem";
import "swiper/css/pagination";

interface FeedGalleryProps extends Pick<Feed, "feedUuid" | "mediaList"> {
  link?: boolean;
}

export function FeedGallery({ feedUuid, mediaList, link }: FeedGalleryProps) {
  if (mediaList.length === 0) return null;

  return (
    <Swiper className="size-full" modules={[Pagination]} pagination>
      {mediaList.map((media) => {
        if (link)
          return (
            <SwiperSlide key={media.mediaUuid}>
              <Link href={`/feeds/${feedUuid}`}>
                <FeedGalleryItem
                  media={media}
                  className="rounded-lg overflow-hidden"
                />
              </Link>
            </SwiperSlide>
          );
        return (
          <SwiperSlide key={media.mediaUuid}>
            <FeedGalleryItem
              key={media.mediaUuid}
              media={media}
              className="rounded-lg overflow-hidden"
            />
          </SwiperSlide>
        );
      })}
    </Swiper>
  );
}

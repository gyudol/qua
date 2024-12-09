"use client";

import { Swiper, SwiperSlide } from "swiper/react";
import type { Swiper as SwiperClass } from "swiper/types"; // Swiper instance 타입 가져오기
import { useState } from "react";
import { CommentDrawerContextProvider } from "@/provider/CommentDrawerContextProvider";
import { useCommentDrawerContext } from "@/context/DrawerContext";
import { useGetShortsRecsInfiniteQuery } from "@/hooks/shorts-read-service";
import ShortsCommentDrawer from "../organisms/ShortsCommentDrawer";
import { ShortsSlideContent } from "../organisms/ShortsSlideContent";

function ShortsSwiper() {
  const { data } = useGetShortsRecsInfiniteQuery({});

  const { setCommentTarget } = useCommentDrawerContext();

  const [activeIndex, setActiveIndex] = useState(0); // 현재 활성 슬라이드 인덱스
  const handleSlideChange = (swiper: SwiperClass) => {
    setActiveIndex(swiper.activeIndex); // 활성 슬라이드 업데이트
    if (setCommentTarget)
      setCommentTarget(() => {
        const targetUuid =
          swiper.slides[swiper.activeIndex].ariaDescription || "";
        return {
          targetType: "shorts",
          targetUuid,
        };
      });
  };

  return (
    <Swiper
      direction="vertical" // 수직 스와이프
      onSlideChange={handleSlideChange}
      className="h-[calc(100vh-5.5rem)] bg-black"
    >
      {data?.pages.map((page) =>
        page.content.map((shorts, index) => (
          <SwiperSlide
            key={shorts.shortsUuid}
            aria-description={shorts.shortsUuid}
            className="flex justify-center items-center relative overflow-hidden"
          >
            <ShortsSlideContent {...shorts} isActive={index === activeIndex} />
          </SwiperSlide>
        )),
      )}
    </Swiper>
  );
}

export function ShortsSection() {
  return (
    <CommentDrawerContextProvider>
      <ShortsSwiper />
      <ShortsCommentDrawer />
    </CommentDrawerContextProvider>
  );
}

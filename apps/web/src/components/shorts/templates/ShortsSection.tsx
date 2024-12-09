"use client";

import { Swiper, SwiperSlide } from "swiper/react";
import type { Swiper as SwiperClass } from "swiper/types"; // Swiper instance 타입 가져오기
import { useState } from "react";
import { CommentDrawerContextProvider } from "@/provider/CommentDrawerContextProvider";
import { useCommentDrawerContext } from "@/context/DrawerContext";
import { SHORTS_DATA, SHORTS_LIST_DATA } from "@/dummies/shorts-data";
import { ShortsButtonGroup, ShortsPannel } from "../molecules";
import ShortsCommentDrawer from "../organisms/ShortsCommentDrawer";
import ShortsVideo from "../organisms/ShortsVideo";

function ShortsSlideContent({
  shortsUuid,
  isActive,
}: {
  shortsUuid: string;
  isActive: boolean;
}) {
  const shortsData = SHORTS_DATA[shortsUuid];

  return (
    <>
      <div className="w-full h-[50%] translate-y-[50%] flex items-center">
        <ShortsVideo src={shortsData.mediaUrl} isActive={isActive} />
      </div>
      <ShortsButtonGroup />
      <ShortsPannel {...shortsData} />
    </>
  );
}

function ShortsSwiper() {
  const { setCommentTarget } = useCommentDrawerContext();

  const [activeIndex, setActiveIndex] = useState(0); // 현재 활성 슬라이드 인덱스
  const handleSlideChange = (swiper: SwiperClass) => {
    setActiveIndex(swiper.activeIndex); // 활성 슬라이드 업데이트
    if (setCommentTarget)
      setCommentTarget(() => {
        return {
          targetType: "shorts",
          targetUuid: SHORTS_LIST_DATA[swiper.activeIndex],
        };
      });
  };

  return (
    <Swiper
      direction="vertical" // 수직 스와이프
      onSlideChange={handleSlideChange}
      className="h-[calc(100vh-5.5rem)] bg-black"
    >
      {SHORTS_LIST_DATA.map((shortsUuid, index) => (
        <SwiperSlide
          key={shortsUuid}
          className="flex justify-center items-center relative overflow-hidden"
        >
          <ShortsSlideContent
            shortsUuid={shortsUuid}
            isActive={index === activeIndex}
          />
        </SwiperSlide>
      ))}
    </Swiper>
  );
}

export function ShortsSection() {
  return (
    <CommentDrawerContextProvider>
      <ShortsSwiper />
      <ShortsCommentDrawer shortsUuid="test" />
    </CommentDrawerContextProvider>
  );
}

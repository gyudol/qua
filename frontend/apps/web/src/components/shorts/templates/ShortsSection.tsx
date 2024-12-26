"use client";

import { Swiper, SwiperSlide } from "swiper/react";
import type { Swiper as SwiperClass } from "swiper/types"; // Swiper instance 타입 가져오기
import { useState } from "react";
import { CommentDrawerContextProvider } from "@/provider/CommentDrawerContextProvider";
import { useCommentDrawerContext } from "@/context/DrawerContext";
import { useGetShortsRecsInfiniteQuery } from "@/hooks/shorts-service";
import { useInfiniteScroll } from "@/hooks";
import ShortsCommentDrawer from "../organisms/ShortsCommentDrawer";
import { ShortsSlideContent } from "../organisms/ShortsSlideContent";

function ShortsSwiper() {
  const {
    data,
    status,
    error,
    hasNextPage,
    isFetchingNextPage,
    fetchNextPage,
  } = useGetShortsRecsInfiniteQuery({
    pageSize: 10,
  });

  const { setCommentTarget } = useCommentDrawerContext();
  const observerRef = useInfiniteScroll({
    hasNextPage,
    isFetchingNextPage,
    fetchNextPage,
  });

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

  if (status === "error") throw Error(error.message);
  if (status === "pending") return <div className="w-full h-full bg-black" />;

  return data.pages[0].content.length ? (
    <Swiper
      direction="vertical" // 수직 스와이프
      onSlideChange={handleSlideChange}
      className="size-full bg-black"
    >
      {data.pages.map((page, pageIndex) =>
        page.content.map((shorts, shortsIndex, arr) => (
          <SwiperSlide
            key={shorts.shortsUuid}
            aria-description={shorts.shortsUuid}
            className="size-full flex justify-center items-center relative overflow-hidden"
          >
            {arr.length === shortsIndex + 4 ? (
              <div ref={observerRef} className="w-full" />
            ) : null}
            <ShortsSlideContent
              {...shorts}
              isActive={pageIndex * 10 + shortsIndex === activeIndex}
            />
          </SwiperSlide>
        )),
      )}
    </Swiper>
  ) : (
    <div className="w-full h-full flex justify-center items-center bg-teal-300">
      <h2 className="text-xl text-white font-extrabold">
        첫 번째 쇼츠를 업로드해보세요!
      </h2>
    </div>
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

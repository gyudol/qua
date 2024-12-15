"use client";

import Image from "next/image";
import { Pagination } from "swiper/modules";
import { Swiper, SwiperSlide } from "swiper/react";
import "swiper/css/pagination";

export default function ContestSwiper() {
  const imageUrls = ["/contest1.png", "/contest2.png"];
  return (
    <Swiper className="w-full" modules={[Pagination]} pagination>
      {imageUrls.map((imageUrl) => (
        <SwiperSlide className="w-full" key={imageUrl}>
          <div className="px-[1rem] py-[2rem] w-full">
            <div className="p-[1rem] flex items-center bg-sky-100 rounded-2xl s">
              <div className="flex flex-col">
                <div className="text-lg font-bold text-teal-600">Event</div>
                <div className="text-sm font-bold text-teal-600">
                  꾸아와 함께하는 콘테스트
                </div>
              </div>
              <div className="flex-1 flex justify-center items-center">
                <figure className="absolute size-[9rem]">
                  <Image src={imageUrl} alt="콘테스트" fill objectFit="cover" />
                </figure>
              </div>
            </div>
          </div>
        </SwiperSlide>
      ))}
    </Swiper>
  );
}

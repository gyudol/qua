import React from 'react';
import { fishCategoryData } from '@/store/InitialData';
import type { FishCategory } from '../common/organisms/SideBar';

function Aquarium({
  size = 300,
  speed = 20,
}: {
  size?: number;
  speed?: number;
}) {
  const data = fishCategoryData as FishCategory[];

  return (
    <>
      {data.map((category) => (
        <div
          key={category.id}
          className="animate-swim fixed top-0 left-0 pointer-events-none"
          style={{
            top: `${Math.random() * 110}%`, // 랜덤 Y축 위치
            right: `${Math.random() * 90}%`, // 랜덤 X축 위치
            zIndex: Math.floor(Math.random() * 10), // 랜덤 z-index
            filter: `blur(${Math.floor(Math.random() * 5)}px)`, // z-index에 따라 blur 조정
            animationDuration: `${Math.random() * speed + 30}s`,
            animationDelay: `${Math.random() * 3}s`, // 랜덤 시작 시간 지연 (0초 ~ 3초)
          }}
        >
          <div className="flex flex-col items-center justify-between">
            {/* <p className="text-white text-xs font-bold bg-[#FFFFFF60] w-fit py-1 px-2 rounded-full">
              {category.name}
            </p> */}
            <category.image
              size={Math.floor(Math.random() * (size - 40 + 1)) + 20}
            />
          </div>
        </div>
      ))}
    </>
  );
}

export default Aquarium;

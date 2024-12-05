'use client';

import React, { useMemo } from 'react';
import { fishCategoryData } from '@/store/InitialData';
import type { FishCategory } from '../common/organisms/SideBar';

function Aquarium({
  size = 300,
  speed = 20,
}: {
  size?: number;
  speed?: number;
}) {
  // 랜덤 데이터 메모화
  const randomizedData = useMemo(() => {
    const data = fishCategoryData as FishCategory[];

    return data.map((category) => ({
      id: category.id.toString(),
      name: category.name,
      image: category.image as React.ComponentType<{ size: number }>,
      style: {
        top: `${Math.random() * 110}%`,
        right: `${Math.random() * 90}%`,
        zIndex: Math.floor(Math.random() * 10),
        filter: `blur(${Math.floor(Math.random() * 5)}px)`,
        animationDuration: `${Math.random() * speed + 30}s`,
        animationDelay: `${Math.random() * 3}s`,
      },
    }));
  }, [speed]); // speed가 변경될 때만 재계산

  return (
    <>
      {randomizedData.map((category) => (
        <div
          key={category.id}
          className="animate-swim fixed top-0 left-0 pointer-events-none"
          style={category.style}
        >
          <div className="flex flex-col items-center justify-between">
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

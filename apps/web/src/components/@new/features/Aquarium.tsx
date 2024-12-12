"use client";

import React, { useLayoutEffect, useState } from "react";
import { ALL_FISH } from "@/components/fish";

interface RandomFish {
  id: string | number;
  Fish: ({
    size,
    className,
  }: {
    size: number;
    className?: string;
  }) => React.JSX.Element;
  size: number;
  style: Record<string, string | number>;
  animation: {
    animationDuration: string;
    animationDelay: string;
  };
}

function Aquarium({
  count = 20,
  size: _size = 50,
  speed = 20,
}: {
  count?: number;
  size?: number;
  speed?: number;
}) {
  // 랜덤 데이터 메모화
  const [randomFishList, setRandomFishList] = useState<RandomFish[]>([]);

  useLayoutEffect(() => {
    setRandomFishList(
      Array.from({ length: count }, (_) => {
        const index = Math.floor(Math.random() * ALL_FISH.length);
        const distance = Math.random();
        return {
          id: index,
          Fish: ALL_FISH[index],
          size: _size * (1 - distance),
          style: {
            top: `${10 + Math.random() * 80}%`,
            right: `${Math.random() * 90}%`,
            zIndex: -(distance * 100),
            // filter: `blur(${Math.floor(distance * 5)}px)`,
          },
          animation: {
            animationDuration: `${Math.random() * speed + 30}s`,
            animationDelay: `${Math.random() * 10}s`,
          },
        };
      }),
    );
  }, [speed]);
  // speed가 변경될 때만 재계산

  return (
    <>
      {randomFishList.map(({ id, Fish, size, style, animation }) => (
        <div
          key={id}
          className="
          absolute
          animate-back-and-forth
          pointer-events-none"
          style={{ ...style, ...animation }}
        >
          <div
            className="
          flex flex-col 
          items-center justify-between
          animate-up-down
          "
          >
            <div
              className="animate-flipping"
              style={{ ...animation, translate: "" }}
            >
              <Fish {...{ size }} />
            </div>
          </div>
        </div>
      ))}
    </>
  );
}

export default Aquarium;

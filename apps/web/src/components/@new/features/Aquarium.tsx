"use client";

import React, { useLayoutEffect, useState } from "react";
import { ALL_FISH } from "@/components/fish";

interface RandomFishState {
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
    // animationDelay: string;
  };
}

function Aquarium({
  count = 10,
  size = 150,
  speed = 20,
}: {
  count?: number;
  size?: number;
  speed?: number;
}) {
  return Array.from({ length: count }).map((_, i) => (
    <RandomFish key={i} {...{ size, speed }} />
  ));
}

export default Aquarium;

function RandomFish({ size: _size, speed }: { size: number; speed: number }) {
  const [randomState, setRandomState] = useState<RandomFishState | null>(null);

  useLayoutEffect(() => {
    const index = Math.floor(Math.random() * ALL_FISH.length);
    const distance = Math.random();
    setRandomState({
      Fish: ALL_FISH[index],
      size: _size * (1.5 - distance),
      style: {
        top: `${10 + Math.random() * 80}%`,
        right: `${Math.random() * 90}%`,
        zIndex: -(distance * 100),
        filter: `blur(${Math.floor(distance)}px)`,
      },
      animation: {
        animationDuration: `${Math.random() * speed + speed}s`,
        // animationDelay: `${Math.random() * 10}s`,
      },
    });
  }, [_size, speed]);

  if (!randomState) return null;
  const { Fish, size, style, animation } = randomState;

  return (
    <div
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
        style={{ ...animation }}
      >
        <div className="animate-flipping" style={{ ...animation }}>
          <Fish {...{ size }} />
        </div>
      </div>
    </div>
  );
}

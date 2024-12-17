"use client";

import React, { useLayoutEffect, useState } from "react";
import { cn } from "@repo/ui/lib/utils";
import { ALL_FISH } from "@/components/fish";
import type { Hashtag } from "@/types/contents";

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
  size = 100,
  speed = 20,
  tags = [],
}: {
  count?: number;
  size?: number;
  speed?: number;
  tags?: Hashtag[];
}) {
  return [...tags, ...Array.from({ length: count }, () => undefined)].map(
    (tag, index, arr) => (
      <RandomFish
        key={index}
        {...{ size, speed, tag, distance: index / arr.length, index }}
      />
    ),
  );
}

export default Aquarium;

function RandomFish({
  size: _size,
  speed,
  // tag,
  distance,
  index,
}: {
  size: number;
  speed: number;
  tag?: Hashtag;
  distance: number;
  index: number;
}) {
  const [randomState, setRandomState] = useState<RandomFishState | null>(null);

  useLayoutEffect(() => {
    setRandomState({
      Fish: ALL_FISH[(index * 7) % ALL_FISH.length],
      size: _size * (2 - distance),
      style: {
        top: `${5 + ((index * 13) % 90)}%`,
        right: `${Math.random() * 90}%`,
        zIndex: -(distance * 100),
        filter: `blur(${distance * 4}px)`,
      },
      animation: {
        animationDuration: `${Math.random() * speed + speed}s`,
        // animationDelay: `${Math.random() * 10}s`,
      },
    });
  }, [_size, speed]);

  if (!randomState) return null;
  const {
    Fish,
    size,
    style: { ...style },
    animation,
  } = randomState;

  return (
    <div
      className={cn(
        "absolute",
        index % 2 ? "animate-back-and-forth" : "animate-back-and-forth-1",
      )}
      style={{ ...style, ...animation }}
    >
      <div
        className="
        flex flex-col 
        items-center justify-between
        "
        style={{ ...animation }}
      >
        {/* {tag ? <div className="text-white font-bold">#{tag.name}</div> : null} */}
        <div
          className={cn(index % 2 ? "animate-flipping" : "animate-flipping-1")}
          style={{ ...animation }}
        >
          <Fish {...{ size }} />
        </div>
      </div>
    </div>
  );
}

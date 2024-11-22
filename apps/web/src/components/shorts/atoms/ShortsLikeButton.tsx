"use client";

import { cn } from "@repo/ui/lib/utils";
import { Heart } from "lucide-react";
import { useState } from "react";

export function ShortsLikeButton() {
  const [isLiked, setIsLiked] = useState(false);

  const handleClick = () => {
    setIsLiked((wasLiked) => !wasLiked);
  };

  return (
    <button
      type="button"
      className={cn("p-2 rounded-full bg-gray-800 bg-opacity-50")}
      onClick={() => handleClick()}
    >
      <Heart
        stroke={isLiked ? "red" : "white"}
        fill={isLiked ? "red" : "none"}
        className="w-6 h-6"
      />
    </button>
  );
}

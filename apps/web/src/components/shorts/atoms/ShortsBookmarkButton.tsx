import { cn } from "@repo/ui/lib/utils";
import { Bookmark } from "lucide-react";
import { useState } from "react";

export function ShortsBookmarkButton() {
  const [isBookmarked, setIsBookmarked] = useState(false);

  const handleClick = () => {
    setIsBookmarked((wasBookmarked) => !wasBookmarked);
  };

  return (
    <button
      type="button"
      className={cn("p-2 rounded-full bg-gray-800 bg-opacity-50")}
      onClick={() => handleClick()}
    >
      <Bookmark
        stroke={isBookmarked ? "white" : "white"}
        fill={isBookmarked ? "white" : "none"}
        className="w-6 h-6"
      />
    </button>
  );
}

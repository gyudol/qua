import React from "react";
import { cn } from "@repo/ui/lib/utils";
import { useRouter, useSearchParams } from "next/navigation";
import { Group, List } from "@/components/common/icons";
import SortSelector from "../atoms/SortSelector";

interface FeedSortTapProps {
  name: string;
  url: string;
  icon: React.FC<{ fill: string }>;
}

function FeedSortTap() {
  const router = useRouter();
  const query = useSearchParams();
  const viewType = query.get("viewType");
  const GRID_VIEW = [
    { name: "list_view", url: `?viewType=list_view`, icon: List },
    { name: "grid_view", url: `?viewType=grid_view`, icon: Group },
  ] as FeedSortTapProps[];

  return (
    <section
      className={cn(
        "sticky top-0 z-10",
        "w-full flex justify-between items-center",
        "p-5 mb-2 mt-[5.43rem]",
        "bg-white",
      )}
    >
      <SortSelector />
      <ul className="flex items-center gap-5">
        {GRID_VIEW.map((view) => (
          <li key={view.name}>
            <button type="button" onClick={() => router.push(view.url)}>
              {view.icon({
                fill: viewType === view.name ? "#48d0bf" : "lightgray",
              })}
            </button>
          </li>
        ))}
      </ul>
    </section>
  );
}

export default FeedSortTap;

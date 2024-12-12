"use client";

import { LayoutGrid, List } from "lucide-react";
import { useRouter, useSearchParams } from "next/navigation";
import { toURLSearchParams } from "@/functions/utils";

export function FeedViewTypeToggler() {
  const router = useRouter();
  const searchParams = Object.fromEntries(useSearchParams().entries());

  const { view } = searchParams;

  const views = [
    { value: "compact", Icon: List },
    { value: "card", Icon: LayoutGrid },
  ];

  function handleClick(value: string) {
    router.push(`?${toURLSearchParams({ ...searchParams, view: value })}`);
  }

  return (
    <ul className="flex gap-[1rem] items-center">
      {views.map(({ value, Icon }) => (
        <li key={value} className="flex items-center">
          <button type="button" onClick={() => handleClick(value)}>
            <Icon
              className={view === value ? "text-teal-400" : "text-slate-400"}
              color={view === value ? "#48d0bf" : "lightgray"}
            />
          </button>
        </li>
      ))}
    </ul>
  );
}

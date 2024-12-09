"use client";

import { Rows2, Rows4 } from "lucide-react";
import { useRouter, useSearchParams } from "next/navigation";
import { toURLSearchParams } from "@/functions/utils";

export function FeedViewTypeToggler() {
  const router = useRouter();
  const searchParams = Object.fromEntries(useSearchParams().entries());

  const { view } = searchParams;

  const views = [
    { value: "card", Icon: Rows2 },
    { value: "compact", Icon: Rows4 },
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

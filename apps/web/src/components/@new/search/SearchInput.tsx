"use client";

import { useSearchParams } from "next/navigation";

export default function SearchInput() {
  const tag = useSearchParams().get("tag") || "";

  return (
    <input
      id="tag"
      name="tag"
      type="text"
      className="flex-1 bg-slate-200 focus:outline-none"
      placeholder="검색어를 입력하세요"
      defaultValue={tag}
    />
  );
}

"use client";

import { cn } from "@repo/ui/lib/utils";
import { XIcon } from "lucide-react";
import Link from "next/link";
import { Menu } from "@/components/common/icons";
import { useGetAllCategories } from "@/hooks/admin-service";
import type { Category } from "@/types/admin";
import { useGetRamdomHashtags } from "@/hooks";
import type { Hashtag } from "@/types/contents";
import { useSidebarContext } from "../../context/SidebarContext";
import Bubbles from "../../features/Bubbles";
import Aquarium from "../../features/Aquarium";

function CategoryListItem({ categoryName, viewType }: Category) {
  return (
    <Link href={`/category/${categoryName}?view=${viewType.toLowerCase()}`}>
      <span
        className="
        py-1  px-2  rounded-2xl
        bg-[rgba(255,255,255,0.3)]
      text-white text-md font-bold
        "
      >
        📚 {categoryName}
      </span>
    </Link>
  );
}

function CategoryList({ categories }: { categories: Category[] }) {
  return (
    <ul className="flex gap-2 flex-wrap">
      {categories.map(({ categoryId, categoryName, viewType }) => (
        <li key={categoryId}>
          <CategoryListItem {...{ categoryId, categoryName, viewType }} />
        </li>
      ))}
    </ul>
  );
}

function HashtagListItem({ name }: Hashtag) {
  const { setIsOpen } = useSidebarContext();
  return (
    <Link href={`/search?keyword=%23${name}`} onClick={() => setIsOpen(false)}>
      <span
        className="
        py-1  px-2  rounded-2xl
      bg-[rgba(255,255,255,0.3)]
      text-white text-md font-bold
        "
      >
        #{name}
      </span>
    </Link>
  );
}

function HashtagList({ hashtags }: { hashtags: Hashtag[] }) {
  return (
    <ul className="flex gap-3 flex-wrap">
      {hashtags.map(({ name }) => (
        <li key={name}>
          <HashtagListItem {...{ name }} />
        </li>
      ))}
    </ul>
  );
}

export function Sidebar() {
  const { isOpen, setIsOpen } = useSidebarContext();
  const onClick = () => setIsOpen((prev) => !prev);
  const { data: hashtags, status: hashtagsStat } = useGetRamdomHashtags({
    size: 10,
  });
  const { data: categories, status: categoriesStat } = useGetAllCategories();
  return (
    <aside
      className={cn(
        "absolute z-50 size-full p-[2rem] transition-all overflow-hidden",
        "flex flex-col justify-between",
        isOpen ? "translate-x" : "-translate-x-full",
      )}
      style={{
        animation: "gradient-bg 3s infinite",
        background: "linear-gradient(35deg, #A3E1EF, #7BD3E5, #0A99B7)",
        backgroundSize: "200% 100%",
      }}
    >
      <Bubbles />
      <header
        className="
        w-full
        z-[150]
        flex flex-col gap-[1rem]
        "
      >
        <div className="flex justify-between items-center">
          <h2 className="text-[1.5rem] font-bold text-white">Category</h2>
          <button type="button" {...{ onClick }}>
            <XIcon className="stroke-white" />
          </button>
        </div>
        {categoriesStat === "success" ? (
          <CategoryList {...{ categories }} />
        ) : null}
      </header>
      <div className="relative flex-1">
        <Aquarium tags={hashtagsStat === "success" ? hashtags : undefined} />
      </div>

      <footer className="z-[150] flex flex-col gap-[0.5rem]">
        <h2 className="text-[1.5rem] font-bold text-white">#Hashtag</h2>
        {hashtagsStat === "success" ? <HashtagList {...{ hashtags }} /> : null}
      </footer>
    </aside>
  );
}

export function SidebarButton() {
  const { setIsOpen } = useSidebarContext();
  const onClick = () => setIsOpen((prev) => !prev);

  return (
    <button type="button" {...{ onClick }}>
      <Menu />
    </button>
  );
}

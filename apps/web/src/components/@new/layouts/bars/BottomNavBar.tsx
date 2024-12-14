"use client";

import { cn } from "@repo/ui/lib/utils";
import type { LucideProps } from "lucide-react";
import { Home, PanelTop, Plus, Search, UserRound, Video } from "lucide-react";
import Link from "next/link";
import { useSelectedLayoutSegment } from "next/navigation";
import React, { useRef, useState } from "react";

function PostButton() {
  const ref = useRef<HTMLUListElement | null>(null);
  const [isFocused, setIsFocused] = useState<boolean>(false);

  const onClick = () => {
    setIsFocused((prev) => {
      if (!prev && ref.current) {
        ref.current.focus();
      }
      return !prev;
    });
  };
  const onBlur = () => {
    setIsFocused(false);
  };

  return (
    <div className="relative flex justify-center items-center">
      <ul
        className={cn(
          "absolute z-[200]",
          "bg-teal-400 text-white",
          "flex text-nowrap",
          "rounded-lg",
          "transition-all",
          isFocused
            ? "translate-y-[-4rem] opacity-100"
            : "translate-y-[-3rem] pointer-events-none opacity-0",
        )}
        {...{ ref, onBlur }}
      >
        <li>
          <Link href="/feeds/write" className="px-4 py-2 flex gap-2">
            <PanelTop /> 피드
          </Link>
        </li>
        <li>
          <Link href="/shorts/write" className="px-4 py-2 flex gap-2">
            <Video />
            쇼츠
          </Link>
        </li>
      </ul>

      <button
        type="button"
        className={cn(
          "size-[3rem] bg-teal-400 rounded-full",
          "flex  justify-center  items-center",
          "transition-all",
          isFocused ? "-rotate-45" : "",
        )}
        {...{ onClick }}
      >
        <Plus size="2.5rem" className="stroke-white" />
      </button>
    </div>
  );
}

type IconType = React.ForwardRefExoticComponent<
  Omit<LucideProps, "ref"> & React.RefAttributes<SVGSVGElement>
>;

function NavLink({
  href,
  segments,
  Icon,
}: {
  href: string;
  segments: string[];
  Icon: IconType;
}) {
  const current = useSelectedLayoutSegment() || "";
  return (
    <Link {...{ href }}>
      <Icon
        size="2.5rem"
        className={cn(
          "p-[0.5rem]",
          segments.includes(current)
            ? "stroke-teal-400 fill-teal-400"
            : "stroke-slate-400",
        )}
      />
    </Link>
  );
}

export default function BottomNavBar() {
  return (
    <>
      <div className="w-full min-h-[4rem]" />
      <nav
        className="
      absolute bottom-0 bg-white
      w-full  h-[4rem]
      flex  flex-row
      justify-around  items-center
      border-t-2
      "
      >
        <NavLink href="/feeds" segments={["feeds", "(main)"]} Icon={Home} />
        <NavLink href="/search" segments={["search"]} Icon={Search} />
        <PostButton />
        <NavLink href="/shorts" segments={["shorts"]} Icon={Video} />
        <NavLink href="/my" segments={["my"]} Icon={UserRound} />
      </nav>
    </>
  );
}

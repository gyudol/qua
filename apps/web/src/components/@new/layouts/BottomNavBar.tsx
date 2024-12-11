"use client";

import { cn } from "@repo/ui/lib/utils";
import type { LucideProps } from "lucide-react";
import { Home, Search, UserRound, Video } from "lucide-react";
import Link from "next/link";
import { useSelectedLayoutSegments } from "next/navigation";
import React from "react";

type IconType = React.ForwardRefExoticComponent<
  Omit<LucideProps, "ref"> & React.RefAttributes<SVGSVGElement>
>;

function NavLink({ segment, Icon }: { segment: string; Icon: IconType }) {
  const segments = useSelectedLayoutSegments();
  return (
    <Link href={`/new/${segment}`}>
      <Icon
        size="3rem"
        className={cn(
          "p-[0.5rem]",
          (segments.length !== 0 && segment === segments[0]) ||
            (segments.length === 0 && segment === "feed")
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
      <div className="w-full min-h-[5rem]" />
      <nav
        className="
      absolute bottom-0 bg-white
      w-full  h-[5rem]
      flex  flex-row
      justify-around  items-center
      border-t-2
      "
      >
        <NavLink segment="feed" Icon={Home} />
        <NavLink segment="search" Icon={Search} />
        <NavLink segment="shorts" Icon={Video} />
        <NavLink segment="my" Icon={UserRound} />
      </nav>
    </>
  );
}

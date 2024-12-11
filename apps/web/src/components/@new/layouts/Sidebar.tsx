"use client";

import { cn } from "@repo/ui/lib/utils";
import { XIcon } from "lucide-react";
import { Menu } from "@/components/common/icons";
import { useSidebarContext } from "../context/SidebarContext";

export function Sidebar() {
  const { isOpen, setIsOpen } = useSidebarContext();
  const onClick = () => setIsOpen((prev) => !prev);

  return (
    <aside
      className={cn(
        "absolute z-50 w-full h-full bg-blue-500 transition-all",
        isOpen ? "translate-x" : "-translate-x-full",
      )}
    >
      <button type="button" {...{ onClick }}>
        <XIcon />
      </button>
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

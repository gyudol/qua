"use client";

import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from "@repo/ui/shadcn/dropdown-menu";
import { MoreVertical } from "lucide-react";
import { cn } from "@repo/ui/lib/utils";
import { useSessionContext } from "@/context/SessionContext";
import type { Shorts } from "@/types/shorts/shorts-read-service";
import { ShortsDeleteButton, ShortsReportButton } from "../atoms";

type ShortsMoreOptionProps = Pick<Shorts, "shortsUuid" | "memberUuid">;

export function ShortsMoreOption({
  shortsUuid,
  memberUuid,
}: ShortsMoreOptionProps) {
  const { memberUuid: sessionUuid } = useSessionContext();

  return (
    <DropdownMenu>
      <DropdownMenuTrigger asChild>
        <button
          type="button"
          className={cn(
            "flex justify-center items-center",
            "size-[3rem] rounded-full",
            "bg-[rgba(0,0,0,0.20)]",
          )}
        >
          <MoreVertical
            size="1.5rem"
            stroke="none"
            className="stroke-white pointer-events-none"
          />
        </button>
      </DropdownMenuTrigger>
      <DropdownMenuContent className="rounded-lg shadow-xl">
        {sessionUuid !== memberUuid ? (
          <DropdownMenuItem className="py-0 text-xs">
            <ShortsReportButton {...{ shortsUuid }} />
          </DropdownMenuItem>
        ) : (
          <>
            {/* <DropdownMenuItem>
              <ShortsEditButton {...{ shortsUuid }} />
            </DropdownMenuItem> */}
            <DropdownMenuItem>
              <ShortsDeleteButton {...{ shortsUuid }} />
            </DropdownMenuItem>
          </>
        )}
      </DropdownMenuContent>
    </DropdownMenu>
  );
}

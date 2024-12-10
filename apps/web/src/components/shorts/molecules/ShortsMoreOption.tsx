"use client";

import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from "@repo/ui/shadcn/dropdown-menu";
import { MoreHorizontal } from "lucide-react";
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
          className="p-1 hover:bg-gray-100 rounded-full text-slate-400"
        >
          <MoreHorizontal size="1.25rem" />
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

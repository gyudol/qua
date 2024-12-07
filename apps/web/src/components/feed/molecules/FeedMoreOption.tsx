import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from "@repo/ui/shadcn/dropdown-menu";
import { MoreHorizontal } from "lucide-react";
import { useSessionContext } from "@/context/SessionContext";
import type { Feed } from "@/types/feed/feed-read-service";
import { FeedReportButton } from "../atoms/FeedReportButton";
import { FeedEditButton } from "../atoms/FeedEditButton";
import { FeedDeleteButton } from "../atoms/FeedDeleteButton";

type FeedMoreOptionProps = Pick<Feed, "feedUuid" | "memberUuid">;

export function FeedMoreOption({ feedUuid, memberUuid }: FeedMoreOptionProps) {
  const { memberUuid: sessionUuid } = useSessionContext();

  return (
    <DropdownMenu>
      <DropdownMenuTrigger className="p-1 hover:bg-gray-100 rounded-full">
        <MoreHorizontal className="w-5 h-5 text-slate-400" />
      </DropdownMenuTrigger>
      <DropdownMenuContent>
        {sessionUuid !== memberUuid ? (
          <DropdownMenuItem>
            <FeedReportButton {...{ feedUuid }} />
          </DropdownMenuItem>
        ) : (
          <>
            <DropdownMenuItem>
              <FeedEditButton {...{ feedUuid }} />
            </DropdownMenuItem>
            <DropdownMenuItem>
              <FeedDeleteButton {...{ feedUuid }} />
            </DropdownMenuItem>
          </>
        )}
      </DropdownMenuContent>
    </DropdownMenu>
  );
}

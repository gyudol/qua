import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from "@repo/ui/shadcn/dropdown-menu";
import { MoreVertical } from "lucide-react";
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
      <DropdownMenuTrigger>
        <button type="button" className="p-1 hover:bg-gray-100 rounded-full">
          <MoreVertical className="w-5 h-5" />
        </button>
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

import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from "@repo/ui/shadcn/dropdown-menu";
import { MoreHorizontal } from "lucide-react";
import { useSessionContext } from "@/context/SessionContext";
import type { Feed } from "@/types/feed/feed-read-service";
import type { MemberProfile } from "@/types/member/member-read-service";
import { FeedReportButton } from "../atoms/FeedReportButton";
// import { FeedEditButton } from '../atoms/FeedEditButton';
import { FeedDeleteButton } from "../atoms/FeedDeleteButton";
// import { FeedChatButton } from "../atoms/FeedChatButton";

type FeedMoreOptionProps = Pick<Feed, "feedUuid" | "memberUuid"> &
  Partial<Pick<MemberProfile, "nickname">>;

export function FeedMoreOption({
  feedUuid,
  memberUuid,
  // nickname,
}: FeedMoreOptionProps) {
  const { memberUuid: sessionUuid } = useSessionContext();

  return (
    <DropdownMenu>
      <DropdownMenuTrigger className="p-1 hover:bg-gray-100 rounded-full">
        <MoreHorizontal className="w-5 h-5 text-slate-400" />
      </DropdownMenuTrigger>
      <DropdownMenuContent className="rounded-lg shadow-xl">
        {sessionUuid !== memberUuid ? (
          <>
            {/* {nickname ? (
              <DropdownMenuItem className="py-0 text-xs">
                <FeedChatButton {...{ nickname }} />
              </DropdownMenuItem>
            ) : null} */}
            <DropdownMenuItem className="py-0 text-xs">
              <FeedReportButton {...{ feedUuid }} />
            </DropdownMenuItem>
          </>
        ) : (
          <>
            {/* <DropdownMenuItem>
              <FeedEditButton {...{ feedUuid }} />
            </DropdownMenuItem> */}
            <DropdownMenuItem>
              <FeedDeleteButton {...{ feedUuid }} />
            </DropdownMenuItem>
          </>
        )}
      </DropdownMenuContent>
    </DropdownMenu>
  );
}

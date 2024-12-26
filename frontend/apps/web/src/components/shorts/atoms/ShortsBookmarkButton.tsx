import { Bookmark } from "lucide-react";
import { cn } from "@repo/ui/lib/utils";
import { ButtonWithAuth } from "@/components/common/atoms";
import {
  useShortsBookmarkMutation,
  useShortsBookmarkStatusQuery,
} from "@/hooks/utility-service/bookmark-service";
import type { Shorts } from "@/types/shorts/shorts-read-service";

type ShortsBookmarkButtonProps = Pick<Shorts, "shortsUuid">;

export function ShortsBookmarkButton({
  shortsUuid,
}: ShortsBookmarkButtonProps) {
  const { data } = useShortsBookmarkStatusQuery({ shortsUuid });
  const { mutate } = useShortsBookmarkMutation({ shortsUuid });

  return (
    <div className="flex flex-col items-center">
      <ButtonWithAuth
        className={cn(
          "flex justify-center items-center",
          "size-[3rem] rounded-full",
          "bg-[rgba(0,0,0,0.20)]",
        )}
        onClick={() => mutate(!data)}
      >
        <Bookmark
          size="1.5rem"
          stroke="none"
          className={data ? "fill-white" : "stroke-white"}
        />
      </ButtonWithAuth>
      <span className="text-sm text-white">저장</span>
    </div>
  );
}

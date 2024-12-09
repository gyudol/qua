import { Bookmark } from "lucide-react";
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
    <ButtonWithAuth
      type="button"
      onClick={() => mutate(!data)}
      className="flex gap-[0.5rem] items-center"
    >
      <span>
        <Bookmark
          className="m-[0.25rem] text-slate-400"
          size="1.25rem"
          fill={data ? "#B1B1B1" : "none"}
        />
      </span>
    </ButtonWithAuth>
  );
}

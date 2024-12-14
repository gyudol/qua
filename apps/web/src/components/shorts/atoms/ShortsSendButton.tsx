import { Send } from "lucide-react";
import { cn } from "@repo/ui/lib/utils";
import type { Shorts } from "@/types/shorts/shorts-read-service";

type ShortsSendButtonProps = Pick<Shorts, "shortsUuid">;

export function ShortsSendButton({ shortsUuid }: ShortsSendButtonProps) {
  function handleClick() {
    if (navigator.canShare()) {
      void navigator.share({
        url: `https://m.qua.world/shorts/${shortsUuid}`,
      });
    } else {
      // eslint-disable-next-line no-alert -- for notice
      alert("Available only in secure contexts.- HTTPS 환경만에서 작동합니다.");
    }

    // toast.success("해당 게시글을 공유하였습니다.(아님)", {
    //   description: feedUuid,
    // });
  }
  return (
    <div className="flex flex-col items-center">
      <button
        type="button"
        className={cn(
          "flex justify-center items-center",
          "size-[3rem] rounded-full",
          "bg-[rgba(0,0,0,0.20)]",
        )}
        onClick={handleClick}
      >
        <Send size="1.5rem" stroke="none" className="stroke-white" />
      </button>
      <span className="text-sm text-white">공유</span>
    </div>
  );
}

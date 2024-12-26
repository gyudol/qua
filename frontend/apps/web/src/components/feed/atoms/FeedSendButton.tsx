import { Send } from "lucide-react";
import type { Feed } from "@/types/feed/feed-read-service";

type FeedSendButtonProps = Pick<Feed, "feedUuid">;

export function FeedSendButton({ feedUuid }: FeedSendButtonProps) {
  function handleClick() {
    if (navigator.canShare()) {
      void navigator.share({
        url: `https://m.qua.world/feeds/${feedUuid}`,
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
    <button
      type="button"
      className="flex gap-[0.5rem] items-center"
      onClick={handleClick}
    >
      <span>
        <Send size="1.25rem" className="text-slate-400" />
      </span>
    </button>
  );
}

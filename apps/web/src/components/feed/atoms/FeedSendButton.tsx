import { Send } from "lucide-react";
import { toast } from "sonner";
import type { Feed } from "@/types/feed/feed-read-service";

type FeedSendButtonProps = Pick<Feed, "feedUuid">;

export function FeedSendButton({ feedUuid }: FeedSendButtonProps) {
  function handleClick() {
    toast.success("해당 게시글을 공유하였습니다.(아님)", {
      description: feedUuid,
    });
  }

  return (
    <button
      type="button"
      className="flex gap-[0.5rem] items-center"
      onClick={handleClick}
    >
      <span>
        <Send size="1.125rem" color="#B1B1B1" />
      </span>
    </button>
  );
}

import { Send } from "lucide-react";
import { toast } from "sonner";
import type { Shorts } from "@/types/shorts/shorts-read-service";

type ShortsSendButtonProps = Pick<Shorts, "shortsUuid">;

export function ShortsSendButton({ shortsUuid }: ShortsSendButtonProps) {
  function handleClick() {
    toast.success("해당 게시글을 공유하였습니다.(아님)", {
      description: shortsUuid,
    });
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

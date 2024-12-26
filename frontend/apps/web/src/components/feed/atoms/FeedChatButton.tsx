"use client";

import { MessageCircle } from "lucide-react";
import { useRouter } from "next/navigation";
import { ButtonWithAuth } from "@/components/common/atoms";
import type { MemberProfile } from "@/types/member/member-read-service";

type FeedChatButtonProps = Pick<MemberProfile, "nickname">;

export function FeedChatButton({ nickname }: FeedChatButtonProps) {
  const router = useRouter();
  function handleClick() {
    router.push(`/chat/rooms/${nickname}`);
  }

  return (
    <ButtonWithAuth
      type="button"
      className="w-full px-4 py-2 text-left hover:bg-gray-50 flex items-center gap-2"
      onClick={handleClick}
    >
      <span>
        <MessageCircle className="w-4 h-4" />
      </span>
      <span>채팅하기</span>
    </ButtonWithAuth>
  );
}

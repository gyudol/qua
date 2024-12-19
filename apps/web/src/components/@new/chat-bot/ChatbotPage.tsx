"use client";

import ChatbotChatContainer, {
  ChatbotChatInputSection,
} from "@/components/@new/chat-bot/ChatbotChatContainer";
import PageContainer from "@/components/@new/layouts/containers/PageContainer";
import type {
  ChatbotCharacter,
  ChatbotChatRecord,
} from "@/types/chat-service/chatbot-service";
import { useState } from "react";

export default function ChatbotPage({
  character,
}: {
  character: ChatbotCharacter;
}) {
  const [newChats, setNewChats] = useState<ChatbotChatRecord[]>([]);
  return (
    <>
      <PageContainer>
        <div className="relative size-full bg-zinc-100">
          <ChatbotChatContainer {...{ character, newChats }} />
        </div>
      </PageContainer>
      <ChatbotChatInputSection {...{ character, setNewChats }} />
    </>
  );
}

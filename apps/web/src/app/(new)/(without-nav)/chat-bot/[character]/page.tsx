import ChatbotChatContainer, {
  ChatbotChatInputSection,
} from "@/components/@new/chat-bot/ChatbotChatContainer";
import PageContainer from "@/components/@new/layouts/containers/PageContainer";
import type { ChatbotCharacter } from "@/types/chat-service/chatbot-service";

export default function page({
  params: { character },
}: {
  params: { character: ChatbotCharacter };
}) {
  return (
    <PageContainer>
      <div className="relative size-full bg-zinc-100">
        <ChatbotChatContainer {...{ character }} />
        <ChatbotChatInputSection {...{ character }} />
      </div>
    </PageContainer>
  );
}

import ChatbotPage from "@/components/@new/chat-bot/ChatbotPage";
import type { ChatbotCharacter } from "@/types/chat-service/chatbot-service";

export default function page({
  params: { character },
}: {
  params: { character: ChatbotCharacter };
}) {
  return <ChatbotPage {...{ character }} />;
}

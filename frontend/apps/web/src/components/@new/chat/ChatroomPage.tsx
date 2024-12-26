import type { CounterPartChatroomInfo } from "@/types/chat-service/chat-service";
import type { MemberProfile } from "@/types/member/member-read-service";

export default function ChatroomPage({
  counterPart,
  chatroom,
}: {
  counterPart: MemberProfile;
  chatroom: CounterPartChatroomInfo;
}) {
  return (
    <section className="size-full">
      {counterPart.nickname}
      {chatroom.newRoom}
    </section>
  );
}

import { CommonLayout } from "@/components/common/atoms";

interface ChatroomPageProp {
  params: {
    chatroomUuid: string;
  };
}

export default function ChatroomPage({
  params: { chatroomUuid },
}: ChatroomPageProp) {
  return <CommonLayout.Contents>채팅방 - {chatroomUuid}</CommonLayout.Contents>;
}

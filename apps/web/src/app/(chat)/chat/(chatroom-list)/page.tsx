import { ChatroomList } from "@/components/chat/templates";
import { CommonLayout } from "@/components/common/atoms";

export default function page() {
  return (
    <CommonLayout.Contents>
      <ChatroomList />
    </CommonLayout.Contents>
  );
}

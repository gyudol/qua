import { postChatroomByCounterPartUuid } from "@/actions/chat-service/chat-service";
import { getMemberProfileByNickname } from "@/actions/member-read-service";
import ChatroomPage from "@/components/@new/chat/ChatroomPage";
import PageContainer from "@/components/@new/layouts/containers/PageContainer";

export default async function page({
  params: { nickname },
}: {
  params: { nickname: string };
}) {
  const counterPart = await getMemberProfileByNickname({
    nickname: decodeURI(nickname),
  });
  const chatroom = await postChatroomByCounterPartUuid({
    counterPartUuid: counterPart.memberUuid,
  });

  return (
    <PageContainer>
      <ChatroomPage {...{ counterPart, chatroom }} />
    </PageContainer>
  );
}

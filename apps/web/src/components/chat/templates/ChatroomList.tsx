import { ChatroomListItem, ChatroomListItemSkeleton } from "../components";

export function ChatroomList() {
  const chatroomUuids = [
    "chatroom-001",
    "chatroom-002",
    "chatroom-003",
    "chatroom-004",
    "chatroom-005",
    "chatroom-006",
    "chatroom-007",
    "chatroom-008",
    "chatroom-009",
  ];

  return (
    <div className="flex flex-col gap-[10px] pt-10 pb-20 bg-white">
      {chatroomUuids.map((chatroomUuid, i) =>
        i < 3 ? (
          <ChatroomListItem key={chatroomUuid} {...{ chatroomUuid }} />
        ) : (
          <ChatroomListItemSkeleton key={chatroomUuid} />
        ),
      )}
    </div>
  );
}

import Link from "next/link";
import { Skeleton } from "@/components/common/atoms";
import { MemberProfileImage } from "@/components/profile/atoms/MemberProfileImage";

interface ChatroomListItemProp {
  chatroomUuid: string;
}

export function ChatroomListItemSkeleton() {
  return (
    <div className="flex gap-x-3 items-center px-7 py-2">
      <div className="flex">
        <Skeleton className="w-[3rem] h-[3rem] rounded-full" />
      </div>
      <div className="flex-1 flex flex-col gap-y-1">
        <Skeleton className="w-16 h-4 rounded-full" />
        <Skeleton className="w-32 h-4 rounded-full" />
      </div>
      <div className="flex flex-col gap-y-1 items-end">
        <Skeleton className="w-10 h-3 rounded-full" />
        <Skeleton className="w-10 h-6 rounded-lg" />
      </div>
    </div>
  );
}

export function ChatroomListItem({ chatroomUuid }: ChatroomListItemProp) {
  const profileImageUrl = "/dummies/members/member-001.png";
  const nickname = chatroomUuid;
  const lastChat = {
    content:
      "채팅을 해봐요 이렇게 채팅을 해봐요 이렇게채팅을 해봐요 이렇게채팅을 해봐요 이렇게",
    createdAt: "9:42 AM",
  };
  const unreadCount = 2;

  // const isLoading = true;
  // if (isLoading) return <ChatroomListItemSkeleton/>

  return (
    <Link
      href={`/chat/${chatroomUuid}`}
      className="flex gap-x-3 items-center px-7 py-2"
    >
      <div className="flex">
        <MemberProfileImage
          {...{ profileImageUrl, nickname, size: "3rem" }}
          link
        />
      </div>
      <div className="flex-1 flex flex-col">
        <div className="h-auto font-bold">{nickname}</div>
        <div className="h-auto w-48 sm:w-72 font-thin text-ellipsis text-nowrap overflow-hidden">
          {lastChat.content}
        </div>
      </div>
      <div className="flex flex-col gap-y-1 items-end">
        <div className="h-4 font-thin text-xs text-nowrap">
          {lastChat.createdAt}
        </div>
        <div className="h-6 px-2 rounded-lg bg-red-500 text-white font-semibold items-center">
          {unreadCount}
        </div>
      </div>
    </Link>
  );
}

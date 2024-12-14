"use client";

import { Send } from "lucide-react";
import { useEffect, useState } from "react";
import { useInfiniteScroll } from "@/hooks";
import {
  useGetChatbotChatMutation,
  useGetChatbotHistoryByCharacterInfiniteQuery,
} from "@/hooks/chat-service/chatbot-service";
import type {
  ChatbotCharacter,
  ChatbotChatRecord,
} from "@/types/chat-service/chatbot-service";

export default function ChatbotChatContainer({
  character,
}: {
  character: ChatbotCharacter;
}) {
  const { data, hasNextPage, isFetchingNextPage, fetchNextPage } =
    useGetChatbotHistoryByCharacterInfiniteQuery({ character, pageSize: 30 });
  const observerRef = useInfiniteScroll({
    hasNextPage,
    isFetchingNextPage,
    fetchNextPage,
  });

  return (
    <div className="w-full flex flex-col-reverse">
      <div ref={observerRef} />
      {data?.pages[0].content.length
        ? data.pages.map((page) =>
            page.content.map((chat) => (
              <ChatbotChat key={chat.createdAt} {...chat} />
            )),
          )
        : null}
    </div>
  );
}

function ChatbotChat({ message }: ChatbotChatRecord) {
  return <div>{message}</div>;
}

export function ChatbotChatInputSection({
  character,
}: {
  character: ChatbotCharacter;
}) {
  const heightPerLine = 1.5;
  const [message, setMessage] = useState<string>("");
  const [line, setLine] = useState(1);
  const { mutate } = useGetChatbotChatMutation({ character });

  useEffect(() => {
    setLine(Math.min(message.split("\n").length, 6));
  }, [message]);

  function onClick() {
    mutate({ message });
    setMessage("");
  }

  return (
    <section
      className="
      w-full absolute bottom-0 
      pt-[1rem] px-[1rem] pb-[2rem]
      flex gap-2 justify-between items-end bg-white
      "
    >
      <textarea
        className="
        flex-1 resize-none border-2 
        focus:outline-none
        p-[0.75rem]
        border-teal-400 rounded-2xl"
        style={{ height: `${(1 + line) * heightPerLine}rem` }}
        value={message}
        onChange={(e) => setMessage(e.target.value)}
      />
      <button
        type="button"
        className="
      flex justify-center items-center
      p-3  bg-teal-400 rounded-full"
        {...{ onClick }}
      >
        <Send className="stroke-white" />
      </button>
      {/* <textarea className="size-full resize-none" /> */}
    </section>
  );
}

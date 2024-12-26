"use client";

import { Send } from "lucide-react";
import type { Dispatch, SetStateAction } from "react";
import { useEffect, useRef, useState } from "react";
import { cn } from "@repo/ui/lib/utils";
import { useInfiniteScroll } from "@/hooks";
import {
  // useGetChatbotChatMutation,
  useGetChatbotHistoryByCharacterInfiniteQuery,
} from "@/hooks/chat-service/chatbot-service";
import type {
  ChatbotCharacter,
  ChatbotChatRecord,
} from "@/types/chat-service/chatbot-service";
import { getChatbotChat } from "@/actions/chat-service/chatbot-service";

export default function ChatbotChatContainer({
  newChats,
  character,
}: {
  character: ChatbotCharacter;
  newChats: ChatbotChatRecord[];
}) {
  const ref = useRef<HTMLDivElement | null>(null);
  const { data, hasNextPage, isFetchingNextPage, fetchNextPage, status } =
    useGetChatbotHistoryByCharacterInfiniteQuery({ character, pageSize: 10 });

  const observerRef = useInfiniteScroll({
    hasNextPage,
    isFetchingNextPage,
    fetchNextPage,
  });

  useEffect(() => {
    if (status === "success" && ref.current) {
      ref.current.scrollTop = ref.current.scrollHeight;
    }
  }, [status]);

  return (
    <>
      <div
        ref={ref}
        className="w-full flex flex-col-reverse px-[1rem] pt-[1rem] gap-4 bg-zinc-100"
      >
        <div ref={observerRef} />
        {data?.pages[0].content.length
          ? data.pages.map((page) =>
              page.content.map((chat) => (
                <ChatbotChat key={chat.createdAt} {...chat} />
              )),
            )
          : null}
      </div>
      <div
        ref={ref}
        className="w-full flex flex-col px-[1rem] pb-[1rem] gap-4 bg-zinc-100"
      >
        {newChats.map((chat) => (
          <ChatbotChat key={chat.createdAt} {...chat} />
        ))}
      </div>
    </>
  );
}

function ChatbotChat({ message, role }: ChatbotChatRecord) {
  return (
    <div
      className={cn(
        "w-full flex",
        role === "assistant" ? "flex-row" : "flex-row-reverse",
      )}
    >
      <div
        className={cn(
          "max-w-[80%] p-2",
          "border ",
          "rounded-lg shadow-md",
          role === "assistant"
            ? "flex-row rounded-tl-none bg-white border-zinc-200"
            : "flex-row-reverse rounded-tr-none bg-teal-400 text-white border-teal-400",
        )}
      >
        {message}
      </div>
    </div>
  );
}

export function ChatbotChatInputSection({
  character,
  setNewChats,
}: {
  character: ChatbotCharacter;
  setNewChats: Dispatch<SetStateAction<ChatbotChatRecord[]>>;
}) {
  const heightPerLine = 1.5;
  const [message, setMessage] = useState<string>("");
  const [line, setLine] = useState(1);
  // const { mutate } = useGetChatbotChatMutation({ character });

  const [answer, setAnswer] = useState<ChatbotChatRecord>();

  useEffect(() => {
    setLine(Math.min(message.split("\n").length, 6));
  }, [message]);

  useEffect(() => {
    if (answer) setNewChats((prev) => [...prev, answer]);
  }, [answer]);

  async function onClick() {
    setMessage("");
    const myChat: ChatbotChatRecord = {
      character,
      role: "user",
      message,
      createdAt: new Date().toString(),
    } as ChatbotChatRecord;
    setNewChats((prev) => [...prev, myChat]);

    const chat = await getChatbotChat({ message, character });
    setAnswer(chat);
  }

  return (
    <>
      <div className="h-[7rem]" />
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
          {...{ onClick: () => void onClick() }}
        >
          <Send className="stroke-white" />
        </button>
        {/* <textarea className="size-full resize-none" /> */}
      </section>
    </>
  );
}

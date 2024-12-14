import Image from "next/image";
import Link from "next/link";
import { getChatbotHistoryByCharacter } from "@/actions/chat-service/chatbot-service";
import PageContainer from "@/components/@new/layouts/containers/PageContainer";
import type { ChatbotCharacter } from "@/types/chat-service/chatbot-service";

interface ChatbotChatroomType {
  imageSrc: string;
  character: ChatbotCharacter;
  name: "니모" | "도리";
}

async function ChatbotChatroomListItem({
  imageSrc,
  character,
  name,
}: ChatbotChatroomType) {
  const { content } = await getChatbotHistoryByCharacter({ character });
  const message = content.length ? content[0].message : `대화를 시작해보세요!`;
  // const createdAt = content.length ? content[0].createdAt : undefined;
  return (
    <Link href={`chat-bot/${character}`}>
      <div className="flex justify-between items-center">
        <div className="flex gap-[1rem] items-center">
          <div>
            <figure className="relative size-[3rem] border-2 border-zinc-100 rounded-full">
              <Image
                className="rounded-full"
                src={`/${imageSrc}`}
                alt={name}
                fill
              />
            </figure>
          </div>
          <div className="flex flex-col">
            <div className="flex gap-2">
              <div className="font-bold">{name}</div>
              <div
                className="
          bg-teal-400 text-white
          text-xs font-extrabold px-2
          flex justify-center items-center
          rounded-2xl"
              >
                bot
              </div>
            </div>
            <p className="text-sm text-slate-400">{message}</p>
          </div>
        </div>
        <div className="flex flex-col">
          <div className="border-teal-400 border-2 text-teal-400 px-2 py-1 rounded-2xl">
            대화하기
          </div>
          {/* <div>
          {createdAt ? <PostedAt {...{createdAt, updatedAt:createdAt}}/> : <span>시작하기</span>}
        </div>
        <div className="text-xs px-2 py-1 bg-red-400 text-white rounded-2xl">200</div> */}
        </div>
      </div>
    </Link>
  );
}

export default function page() {
  const chatbotChatList: ChatbotChatroomType[] = [
    {
      imageSrc: "dori.webp",
      character: "dori",
      name: "도리",
    },
    {
      imageSrc: "nimo.webp",
      character: "nimo",
      name: "니모",
    },
  ];

  return (
    <PageContainer>
      <section className="px-[1rem] py-[2rem]">
        <div className="flex flex-col gap-[1rem]">
          {chatbotChatList.map(({ character, ...info }) => (
            <ChatbotChatroomListItem
              key={character}
              {...{ character, ...info }}
            />
          ))}
        </div>
      </section>
    </PageContainer>
  );
}

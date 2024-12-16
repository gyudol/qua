import { ChevronLeft } from "lucide-react";
import Image from "next/image";
import BackButton from "../common/BackButton";

export default function ChatHeader({
  src,
  nickname,
}: {
  src: string;
  nickname: string;
}) {
  return (
    <>
      <div className="w-full min-h-[4.5rem]" />
      <header
        className="
      absolute top-0  z-30
      w-full  h-[4.5rem]
      flex    flex-row
      px-4    shadow-md
      justify-between items-center
      "
      >
        <div className="text-[1.25rem] font-semibold flex gap-4 items-center">
          <BackButton>
            <ChevronLeft className="stroke-teal-400" />
          </BackButton>
          <figure className="relative size-[2.5rem]">
            <Image className="rounded-full" src={src} alt={nickname} fill />
          </figure>
          {nickname}
        </div>
      </header>
    </>
  );
}

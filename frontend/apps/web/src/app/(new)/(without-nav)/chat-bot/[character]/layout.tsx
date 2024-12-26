import React from "react";
import { redirect } from "next/navigation";
import { getServerSession } from "next-auth";
import ChatHeader from "@/components/@new/chat-bot/ChatHeader";
import { options } from "@/app/api/auth/[...nextauth]/authOption";

export default async function layout({
  children,
  params: { character },
}: {
  children: React.ReactNode;
  params: { character: string };
}) {
  const session = await getServerSession(options);
  if (!session?.user) return redirect("/");

  if (!["dori", "nimo"].includes(character)) redirect("/chat/rooms");
  const src = `/${character}.webp`;
  const nickname = character === "dori" ? "도리" : "니모";

  return (
    <>
      <ChatHeader {...{ src, nickname }} />
      {children}
    </>
  );
}

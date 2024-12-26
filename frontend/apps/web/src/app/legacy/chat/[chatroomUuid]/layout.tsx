import type { Metadata } from "next";
import "@/app/globals.css";
import "@repo/ui/styles.css";
import type { PropsWithChildren } from "react";
import { ChatroomHeader } from "@/components/chat/components";

export const metadata: Metadata = {
  title: "Create Turborepo",
  description: "Generated by create turbo",
};

interface ChatroomLayoutProp extends PropsWithChildren {
  params: {
    chatroomUuid: string;
  };
}

export default function ChatroomLayout({
  children,
  params: { chatroomUuid },
}: ChatroomLayoutProp): JSX.Element {
  return (
    <>
      <ChatroomHeader {...{ chatroomUuid }} />
      {children}
    </>
  );
}
"use client";
import type { PropsWithChildren } from "react";
import type { SessionContextType } from "@/context/SessionContext";
import { SessionContext } from "@/context/SessionContext";

export default function SessionContextProvider({
  children,
  ...session
}: PropsWithChildren & SessionContextType) {
  return (
    <SessionContext.Provider value={session}>
      {children}
    </SessionContext.Provider>
  );
}

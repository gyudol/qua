"use client";

import { SessionProvider as NextAuthSessionProvider } from "next-auth/react";
import type { PropsWithChildren } from "react";

export default function SessionProvider({ children }: PropsWithChildren) {
  return <NextAuthSessionProvider>{children}</NextAuthSessionProvider>;
}

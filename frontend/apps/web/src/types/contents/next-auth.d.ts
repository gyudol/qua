// src/types/next-auth.d.ts
import type { DefaultSession } from "next-auth";

declare module "next-auth" {
  interface Session {
    user: {
      memberUuid: string;
      accessToken: string;
    } & DefaultSession["user"];
  }

  interface User {
    memberUuid: string;
    accessToken: string;
  }

  interface JWT {
    accessToken: string;
    memberUuid: string;
  }
}

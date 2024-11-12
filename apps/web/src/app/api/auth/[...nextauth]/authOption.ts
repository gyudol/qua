import type { NextAuthOptions } from "next-auth";
import KakaoProvider from "next-auth/providers/kakao";
import NaverProvider from "next-auth/providers/naver";

interface AuthResponse {
  memberUuid: string;
  accessToken: string;
  refreshToken: string;
}

const refreshTokenStore: Record<string, string> = {};

export const options: NextAuthOptions = {
  providers: [
    KakaoProvider({
      clientId: process.env.KAKAO_CLIENT_ID || "",
      clientSecret: process.env.KAKAO_CLIENT_SECRET || "",
    }),
    NaverProvider({
      clientId: process.env.NAVER_CLIENT_ID || "",
      clientSecret: process.env.NAVER_CLIENT_SECRET || "",
    }),
  ],
  secret: process.env.NEXTAUTH_SECRET,

  callbacks: {
    async signIn({ user, account }) {
      if (account) {
        const res = await fetch(
          `${process.env.BASE_API_URL}/member-service/v1/auth/sign-in`,
          {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
              oauthId: account.providerAccountId,
              oauthProvider: account.provider,
            }),
          },
        );

        const data = (await res.json()) as AuthResponse;

        if (data.memberUuid && data.accessToken && data.refreshToken) {
          user.memberUuid = data.memberUuid;
          user.accessToken = data.accessToken;
          refreshTokenStore[data.memberUuid] = data.refreshToken;
          return true;
        }
      }
      return false;
    },

    jwt({ token, user }) {
      Object.assign(token, {
        memberUuid: user.memberUuid,
        accessToken: user.accessToken,
      });
      return token;
    },

    session({ session, token }) {
      session.user = {
        ...session.user,
        memberUuid: token.memberUuid as string,
        accessToken: token.accessToken as string,
      };
      session.expires = new Date(
        Date.now() + 24 * 60 * 60 * 1000,
      ).toISOString();
      return session;
    },
  },

  pages: {
    signIn: "/sign-in",
  },
};

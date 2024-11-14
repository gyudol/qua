import type { NextAuthOptions, User as NextAuthUser } from "next-auth";
import KakaoProvider from "next-auth/providers/kakao";
import NaverProvider from "next-auth/providers/naver";

interface ExtendedUser extends NextAuthUser {
  memberUuid: string;
  accessToken: string;
  refreshToken: string;
}

interface AuthResponse {
  memberUuid: string;
  accessToken: string;
  refreshToken: string;
}

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
    async signIn({ user, account, profile }) {
      const extendedUser = user as ExtendedUser; // 확장된 타입으로 캐스팅
      if (profile && account) {
        // console.log(account); // 소셜 로그인 정보 출력
        try {
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

          if (!res.ok) return false;

          const responseData = (await res.json()) as { result: AuthResponse };

          // console.log("Social login response data:", responseData);

          const data = responseData.result;

          if (data.memberUuid && data.accessToken && data.refreshToken) {
            extendedUser.memberUuid = data.memberUuid;
            extendedUser.accessToken = data.accessToken;
            extendedUser.refreshToken = data.refreshToken; // JWT에 저장할 수 있도록 추가
            return true;
          }
          return false;
        } catch (error) {
          // console.error("Error in social login:", error);
          return false;
        }
      }
      return false;
    },

    async jwt({ token, user }) {
      const extendedUser = user as ExtendedUser;
      if (
        extendedUser.memberUuid &&
        extendedUser.accessToken &&
        extendedUser.refreshToken
      ) {
        token.memberUuid = extendedUser.memberUuid;
        token.accessToken = extendedUser.accessToken;
        token.refreshToken = extendedUser.refreshToken;
        token.accessTokenExpires = Date.now() + 3 * 60 * 60 * 1000; // 3시간 후 만료
      }

      if (Date.now() > (token.accessTokenExpires as number)) {
        try {
          const res = await fetch(
            `${process.env.BASE_API_URL}/member-service/v1/auth/refresh-token`,
            {
              method: "POST",
              headers: { "Content-Type": "application/json" },
              body: JSON.stringify({
                memberUuid: token.memberUuid,
                refreshToken: token.refreshToken,
              }),
            },
          );

          if (!res.ok) throw new Error("Failed to refresh access token");

          const responseData = (await res.json()) as { result: AuthResponse };
          const newAccessToken = responseData.result.accessToken;

          // 새 액세스 토큰과 만료 시간 갱신
          token.accessToken = newAccessToken;
          token.accessTokenExpires = Date.now() + 3 * 60 * 60 * 1000; // 3시간 후 만료
        } catch (error) {
          // console.error("Failed to refresh token", error);
          return { ...token, error: "RefreshAccessTokenError" };
        }
      }

      return token;
    },

    session({ session, token }) {
      session.user = {
        ...session.user,
        memberUuid: token.memberUuid as string,
        accessToken: token.accessToken as string,
      };
      session.expires = new Date(
        token.accessTokenExpires as number,
      ).toISOString();
      return session;
    },
  },

  pages: {
    signIn: "/sign-in",
    error: "/error",
  },
};

import type { NextAuthOptions } from "next-auth";
import KakaoProvider from "next-auth/providers/kakao";
import NaverProvider from "next-auth/providers/naver";
import type { CommonRes } from "@/types/common";
import type { MemberSignInResType } from "@/types/member/common";
import Credentials from "next-auth/providers/credentials";

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
    Credentials({
      // The name to display on the sign in form (e.g. 'Sign in with...')
      name: "credentials",
      // The credentials is used to generate a suitable form on the sign in page.
      // You can specify whatever fields you are expecting to be submitted.
      // e.g. domain, username, password, 2FA token, etc.
      // You can pass any HTML attribute to the <input> tag through the object.
      credentials: {},
      async authorize(credentials, req) {
        console.log("--------aaaax");
        const uniqueString = new Date().toString();
        return {
          memberUuid: uniqueString,
          accessToken: "auto-sign-in",
          refreshToken: "",
          nickname: "",
          id: "",
        };
      },
    }),
  ],
  secret: process.env.NEXTAUTH_SECRET,

  callbacks: {
    async signIn({ user, account, profile }) {
      console.log(user);
      if (user.accessToken === "auto-sign-in") {
        try {
          const res = await fetch(
            `${process.env.BASE_API_URL}/member-service/v1/auth/sign-in`,
            {
              method: "POST",
              headers: { "Content-Type": "application/json" },
              body: JSON.stringify({
                oauthId: user.memberUuid,
                oauthProvider: "kakao",
              }),
            },
          );

          if (!res.ok) return false;
          const responseData =
            (await res.json()) as CommonRes<MemberSignInResType>;

          const data = responseData.result as MemberSignInResType;
          user.memberUuid = data.memberUuid;
          user.accessToken = data.accessToken;
          user.refreshToken = data.refreshToken;

          const headers = {
            "Content-Type": "application/json",
            "Member-Uuid": data.memberUuid,
            Authorization: `Bearer ${data.accessToken}`,
          };

          const randomHashtagURI = `${process.env.BASE_API_URL}/feed-read-service/v1/hashtags?size=5`;
          const allCategoryURI = `${process.env.BASE_API_URL}/admin-service/v1/category`;

          const [hashtagsRes, categoriesRes] = await Promise.all([
            fetch(randomHashtagURI, {
              headers,
              method: "GET",
            }),
            fetch(allCategoryURI, {
              headers,
              method: "GET",
            }),
          ]);

          const [hashtagsJson, categoriesJson] = await Promise.all([
            hashtagsRes.json(),
            categoriesRes.json(),
          ]);
          const { result: hashtags }: { result: { name: string }[] } =
            hashtagsJson;
          const {
            result: categories,
          }: {
            result: {
              categoryId: number;
              categoryName: string;
              viewType: string;
            }[];
          } = categoriesJson;

          const i = Math.floor(Math.random() * categories.length);
          const j = Math.floor(Math.random() * categories.length);

          const tagInterestURI = `${process.env.BASE_API_URL}/member-service/v1/members/${data.memberUuid}/interests/hashtags`;
          const categoryInterestURI = `${process.env.BASE_API_URL}/member-service/v1/members/${data.memberUuid}/interests/categories`;

          await Promise.all([
            fetch(tagInterestURI, {
              headers,
              method: "PUT",
              cache: "no-cache",
              body: JSON.stringify({ hashtags }),
            }),
            fetch(categoryInterestURI, {
              headers,
              method: "PUT",
              cache: "no-cache",
              body: JSON.stringify({
                categories: [
                  { id: categories[i].categoryId },
                  { id: categories[j].categoryId },
                ],
              }),
            }),
          ]);

          return true;
        } catch (error) {
          // console.error("Error in social login:", error);
          return false;
        }
      }

      if (profile && account) {
        // console.log(account);
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

          const responseData =
            (await res.json()) as CommonRes<MemberSignInResType>;
          const data = responseData.result as MemberSignInResType;
          user.memberUuid = data.memberUuid;
          user.accessToken = data.accessToken;
          user.refreshToken = data.refreshToken;

          return true;
        } catch (error) {
          // console.error("Error in social login:", error);
          return false;
        }
      }
      return false;
    },

    async jwt({ token, user }) {
      // token.memberUuid = (user as ExtendedUser).memberUuid;
      // token.accessToken = (user as ExtendedUser).accessToken;
      // token.refreshToken = (user as ExtendedUser).refreshToken;

      // const currentTime = Math.floor(Date.now() / 1000); // 현재 시간 (초 단위)
      // const accessTokenExpiry = token.accessTokenExpiry as number | undefined;

      // if (accessTokenExpiry && currentTime > accessTokenExpiry) {
      //   // console.log("Access token expired, refreshing token...");
      //   try {
      //     const res = await fetch(
      //       // 재발급 api
      //       `${process.env.BASE_API_URL}/member-service/v1/auth/token/refresh`,
      //       {
      //         method: "POST",
      //         headers: { "Content-Type": "application/json" },
      //         body: JSON.stringify({
      //           refreshToken: token.refreshToken,
      //           memberUuid: token.memberUuid,
      //         }),
      //       },
      //     );

      //     if (!res.ok) {
      //       // console.error("Failed to refresh access token");
      //       return token; // 기존 토큰 그대로 반환 (갱신 실패 시)
      //     }

      //     const responseData = (await res.json()) as { result: AuthResponse };

      //     // console.log("Token refresh response data:", responseData);

      //     const newTokenData = responseData.result;

      //     if (newTokenData.accessToken) {
      //       token.accessToken = newTokenData.accessToken;
      //       token.accessTokenExpiry = Math.floor(Date.now() / 1000) + 60 * 60;
      //     }
      //   } catch (error) {
      //     // console.error("Error refreshing access token:", error);
      //   }
      // }

      return { ...token, ...user };
    },

    session({ session, token }) {
      session.user = token as any;
      return session;
    },
  },

  pages: {
    signIn: "/sign-in",
    error: "/error",
  },
};

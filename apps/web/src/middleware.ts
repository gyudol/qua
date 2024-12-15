import type { NextRequest } from "next/server";
import { NextResponse } from "next/server";
import { getToken } from "next-auth/jwt";

const routes = {
  signIn: "/sign-in",
  signOut: "/sign-out",
  writeFeed: "/feeds/write",
  writeShorts: "/shorts/write",
  contest: [
    "/contest",
    "/contest/contestform",
    "/contest/contesthistory",
    "/contest/contestlist",
  ],
  mypage: "/my",
};

const FALLBACK_URL = "/";

const withAuthList = [
  routes.mypage,
  routes.signOut,
  routes.writeFeed,
  routes.writeShorts,
  ...routes.contest,
];
const withoutAuthList = [routes.signIn];

const withAuth = (req: NextRequest, token: boolean) => {
  const url = req.nextUrl.clone();
  const { pathname } = req.nextUrl;

  if (!token) {
    url.pathname = routes.signIn;
    url.search = `callbackUrl=${pathname}`;
    return NextResponse.redirect(url);
  }
};

const withoutAuth = (req: NextRequest, token: boolean, to: string | null) => {
  const url = req.nextUrl.clone();

  if (token) {
    url.pathname = to ?? FALLBACK_URL;
    url.search = "";
    return NextResponse.redirect(url);
  }
};

export default async function middleware(request: NextRequest) {
  // ** NextAuth 기반 토큰 가져오기 **
  const authToken = await getToken({
    req: request,
    secret: process.env.NEXTAUTH_SECRET,
  });
  const accessToken = authToken?.accessToken;

  // ** URL 정보 추출 **
  const { pathname } = request.nextUrl;
  const { searchParams } = request.nextUrl;
  const callbackUrl = searchParams.get("callbackUrl");

  // ** 경로에 따른 처리 **
  const isWithAuth = withAuthList.includes(pathname);
  const isWithoutAuth = withoutAuthList.includes(pathname);

  if (isWithAuth) {
    return withAuth(request, Boolean(accessToken));
  } else if (isWithoutAuth) {
    return withoutAuth(request, Boolean(accessToken), callbackUrl);
  }

  // ** 기본 응답 **
  const response = NextResponse.next();
  response.headers.set("siv-pathname", pathname); // 헤더에 pathname 추가
  response.headers.set("siv-params", searchParams.toString());

  return response;
}

export const config = {
  matcher: [
    "/mypage",
    "/((?!api|_next/static|_next/image|favicon.ico|fonts|images).*)",
  ],
};

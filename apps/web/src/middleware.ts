import { NextResponse } from "next/server";
import type { NextRequest } from "next/server";

export const config = {
  matcher: ["/mypage"],
};

export default function middleware(request: NextRequest) {
  const token = request.cookies.get("accessToken");

  if (!token?.value) {
    return NextResponse.redirect(new URL("/sign-in", request.url));
  }

  if (!isValidTokenFormat(token.value)) {
    return NextResponse.redirect(new URL("/sign-in", request.url));
  }

  return NextResponse.next();
}

function isValidTokenFormat(token: string): boolean {
  const jwtPattern = /^[A-Za-z0-9-_]+\.[A-Za-z0-9-_]+\.[A-Za-z0-9-_]+$/;
  return jwtPattern.test(token) && token.length > 30;
}

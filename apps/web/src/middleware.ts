import { NextResponse } from "next/server";
import type { NextRequest } from "next/server";

export const config = {
  matcher: ["/protected/:path*"],
};

export default function middleware(request: NextRequest) {
  const token = request.cookies.get("accessToken");

  if (!token) {
    return NextResponse.redirect(new URL("/sign-in", request.url));
  }

  return NextResponse.next();
}

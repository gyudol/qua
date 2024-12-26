"use client";

import { signIn } from "next-auth/react";

export default function AutoSignIn() {
  void signIn("credentials", { callbackUrl: "/" });
  return <div />;
}

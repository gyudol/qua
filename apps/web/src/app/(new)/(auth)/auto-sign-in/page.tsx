"use client";

import { signIn } from "next-auth/react";

export default async function page() {
  await signIn("credentials", { callbackUrl: "/" });
  return <div />;
  // return void signIn("auto-sign-in", {callbackUrl: "/"})
}

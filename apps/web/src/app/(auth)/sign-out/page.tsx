"use client";

import { signOut } from "next-auth/react";

export default function LogoutButton() {
  return (
    <button onClick={() => void signOut({ callbackUrl: "/" })} type="button">
      로그아웃
    </button>
  );
}

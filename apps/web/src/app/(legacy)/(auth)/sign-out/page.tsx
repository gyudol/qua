"use client";

import { signOut } from "next-auth/react";
import { CommonLayout } from "@/components/common/atoms";

export default function LogoutButton() {
  return (
    <CommonLayout.Contents className="min-h-screen flex justify-center items-center">
      <button onClick={() => void signOut({ callbackUrl: "/" })} type="button">
        로그아웃
      </button>
    </CommonLayout.Contents>
  );
}

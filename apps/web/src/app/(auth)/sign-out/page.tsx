"use client";

import { CommonLayout } from "@/components/common/atoms";
import { signOut } from "next-auth/react";

export default function LogoutButton() {
  return (
    <CommonLayout.Contents>
      <button onClick={() => void signOut({ callbackUrl: "/" })} type="button">
        로그아웃
      </button>
    </CommonLayout.Contents>
  );
}

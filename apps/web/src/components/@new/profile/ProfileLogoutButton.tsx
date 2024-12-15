"use client";

import { cn } from "@repo/ui/lib/utils";
import { signOut } from "next-auth/react";
import { ButtonWithAuth } from "@/components/common/atoms";

export default function ProfileLogoutButton() {
  function handleClick() {
    void signOut({ callbackUrl: "/" });
  }

  return (
    <ButtonWithAuth
      className={cn(
        "flex-1 flex gap-1 rounded-lg",
        "justify-center items-center",
        "bg-white text-teal-400 font-bold border-2 border-teal-400",
        "bg-teal-400 text-white",
      )}
      onClick={handleClick}
    >
      <span className="flex text-[1rem]">로그아웃</span>
    </ButtonWithAuth>
  );
}

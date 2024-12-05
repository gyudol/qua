"use client";

import { useRouter } from "next/navigation";
import { toast } from "sonner";
import { useSessionContext } from "@/context/SessionContext";

export default function ButtonWithAuth({
  onClick,
  children,
  ...props
}: React.ButtonHTMLAttributes<HTMLButtonElement>) {
  const { isAuthenticated } = useSessionContext();
  const router = useRouter();

  function handleClick(e: React.MouseEvent<HTMLButtonElement>) {
    if (isAuthenticated && onClick) return onClick(e);
    return toast.error("로그인이 필요한 작업입니다.", {
      description: "설명",
      action: {
        label: "로그인",
        onClick: () => router.push("/sign-in"),
      },
    });
  }

  return (
    <button type="button" {...{ ...props, onClick: handleClick }}>
      {children}
    </button>
  );
}

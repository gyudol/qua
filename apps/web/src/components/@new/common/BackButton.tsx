"use client";

import { useRouter } from "next/navigation";

export default function BackButton({
  children,
}: {
  children: React.ReactNode;
}) {
  const router = useRouter();
  const onClick = () => router.back();
  return (
    <button type="button" {...{ onClick }}>
      {children}
    </button>
  );
}

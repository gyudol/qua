"use client";

import { signOut } from "next-auth/react";
import PageContainer from "@/components/@new/layouts/containers/PageContainer";

export default function page() {
  return (
    <PageContainer>
      <button onClick={() => void signOut({ callbackUrl: "/" })} type="button">
        로그아웃
      </button>
    </PageContainer>
  );
}

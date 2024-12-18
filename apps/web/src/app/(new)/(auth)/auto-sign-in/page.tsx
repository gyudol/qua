"use client";

import AutoSignIn from "@/components/@new/auth/AutoSignIn";
import PageContainer from "@/components/@new/layouts/containers/PageContainer";

export default function page() {
  return (
    <PageContainer>
      <AutoSignIn />
    </PageContainer>
  );
}

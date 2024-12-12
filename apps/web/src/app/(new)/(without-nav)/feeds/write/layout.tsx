import React from "react";
import DetailHeader from "@/components/@new/layouts/headers/DetailHeader";

export default function layout({ children }: { children: React.ReactNode }) {
  return (
    <>
      <DetailHeader {...{ title: "피드 작성" }} />
      {children}
    </>
  );
}

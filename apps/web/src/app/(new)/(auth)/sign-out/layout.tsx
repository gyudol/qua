import React from "react";
import MainHeader from "@/components/@new/layouts/headers/MainHeader";

export default function layout({ children }: { children: React.ReactNode }) {
  return (
    <>
      <MainHeader />
      {children}
    </>
  );
}

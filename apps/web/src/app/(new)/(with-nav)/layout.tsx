import React from "react";
import BottomNavBar from "@/components/@new/layouts/bars/BottomNavBar";

export default function layout({ children }: { children: React.ReactNode }) {
  return (
    <>
      {children}
      <BottomNavBar />
    </>
  );
}

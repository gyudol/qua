import React from "react";
import { SidebarContextProvider } from "@/components/@new/context/SidebarContext";
import { Sidebar } from "@/components/@new/layouts/Sidebar";
import DetailHeader from "@/components/@new/layouts/DetailHeader";

export default function layout({ children }: { children: React.ReactNode }) {
  return (
    <SidebarContextProvider>
      <Sidebar />
      <DetailHeader />
      {children}
    </SidebarContextProvider>
  );
}

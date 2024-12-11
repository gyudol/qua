import React from "react";
import MainHeader from "@/components/@new/layouts/MainHeader";
import { SidebarContextProvider } from "@/components/@new/context/SidebarContext";
import { Sidebar } from "@/components/@new/layouts/Sidebar";

export default function layout({ children }: { children: React.ReactNode }) {
  return (
    <SidebarContextProvider>
      <Sidebar />
      <MainHeader />
      {children}
    </SidebarContextProvider>
  );
}

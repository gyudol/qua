import React from "react";
import MainHeader from "@/components/@new/layouts/headers/MainHeader";
import { SidebarContextProvider } from "@/components/@new/context/SidebarContext";
import { Sidebar } from "@/components/@new/layouts/bars/Sidebar";

export default function layout({ children }: { children: React.ReactNode }) {
  return (
    <SidebarContextProvider>
      <Sidebar />
      <MainHeader />
      {children}
    </SidebarContextProvider>
  );
}

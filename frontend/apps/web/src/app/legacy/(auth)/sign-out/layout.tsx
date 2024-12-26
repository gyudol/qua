"use client";
import { useState } from "react";
import { FeedTabHeader } from "@/components/feed-tab/organisms";
import { Sidebar } from "@/components/common/organisms/SideBar";

export default function Layout({
  children,
}: {
  children: React.ReactNode;
}): JSX.Element {
  const [sidebarOpen, setSidebarOpen] = useState(false);
  return (
    <>
      <FeedTabHeader onMenuClick={() => setSidebarOpen(true)} />
      <div className={`flex flex-1 ${sidebarOpen} ? : overflow-auto`}>
        <Sidebar open={sidebarOpen} onClose={() => setSidebarOpen(false)} />
      </div>
      {children}
    </>
  );
}

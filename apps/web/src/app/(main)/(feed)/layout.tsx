"use client";
import { useState } from "react";
import { FeedTabHeader } from "@/components/feed-tab/organisms";
import { Sidebar } from "@/components/common/organisms/SideBar";
import InnerMobileContainer from "@/components/layouts/InnerMobileContainer";

export default function Layout({
  children,
}: {
  children: React.ReactNode;
}): JSX.Element {
  const [sidebarOpen, setSidebarOpen] = useState(false);

  return (
    <InnerMobileContainer>
      <Sidebar open={sidebarOpen} onClose={() => setSidebarOpen(false)} />
      <FeedTabHeader onMenuClick={() => setSidebarOpen(true)} />
      {children}
    </InnerMobileContainer>
  );
}

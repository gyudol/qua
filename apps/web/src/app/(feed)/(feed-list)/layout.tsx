"use client";
import { useState } from "react";
import { FeedTabHeader } from "@/components/feed-tab/organisms";
import { Sidebar } from "@/components/common/organisms/SideBar";
import InnerMobileContainer from "@/components/layouts/InnerMobileContainer";
import { CommonGbnb } from "@/components/common/organisms";

interface FeedListLayoutProps {
  children: React.ReactNode;
}

export default function Layout({ children }: FeedListLayoutProps): JSX.Element {
  const [sidebarOpen, setSidebarOpen] = useState(false);

  return (
    <InnerMobileContainer>
      <Sidebar open={sidebarOpen} onClose={() => setSidebarOpen(false)} />
      <FeedTabHeader onMenuClick={() => setSidebarOpen(true)} />
      {children}
      <CommonGbnb />
    </InnerMobileContainer>
  );
}

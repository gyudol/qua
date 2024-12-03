'use client';
import { useState } from 'react';
import { FeedTabHeader } from '@/components/feed-tab/organisms';
import { Sidebar } from '@/components/common/organisms/SideBar';

export default function Layout({
  children,
}: {
  children: React.ReactNode;
}): JSX.Element {
  const [sidebarOpen, setSidebarOpen] = useState(false);

  return (
    <>
      <Sidebar open={sidebarOpen} onClose={() => setSidebarOpen(false)} />
      <FeedTabHeader onMenuClick={() => setSidebarOpen(true)} />
      <div className="relative w-full md:max-w-md mx-auto md:h-[90%] md:absolute md:top-10 md:rounded-xl bg-white shadow-md overflow-hidden flex flex-col">
        <div className="md:flex-1 md:overflow-auto overflow-visible">
          {children}
        </div>
      </div>
    </>
  );
}

"use client";

import { Bell, Globe } from "lucide-react";
import { useRouter } from "next/navigation";
import { Kitty, LeftChevron } from "@/components/common/icons";

function BackButton({ children }: { children: React.ReactNode }) {
  const router = useRouter();
  const onClick = () => router.back();
  return (
    <button type="button" {...{ onClick }}>
      {children}
    </button>
  );
}

export default function MainHeader() {
  return (
    <>
      <div className="w-full min-h-20" />
      <header
        className="
      absolute top-0  z-30
      w-full  h-20 bg
      flex    flex-row
      px-8    border-b-2
      justify-between items-center
      bg-teal-400
      "
      >
        <div className="flex flex-row gap-x-4">
          <BackButton>
            <LeftChevron />
          </BackButton>
          <h1 className="text-[0rem]">QUA</h1>
        </div>
        <div className="flex flex-row gap-x-4 items-center">
          <Globe size="1.5rem" className="stroke-white" />
          <Bell size="1.5rem" className="stroke-white" />
          <Kitty />
        </div>
      </header>
    </>
  );
}

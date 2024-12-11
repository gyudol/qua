import { Bell, Globe } from "lucide-react";
import { Kitty, Logo } from "@/components/common/icons";
import { SidebarButton } from "./Sidebar";

export default function MainHeader() {
  return (
    <>
      <div className="w-full min-h-[6rem]" />
      <header
        className="
      absolute top-0  z-30
      w-full  h-24 bg
      flex    flex-row
      px-8    border-b-2
      justify-between items-center
      "
      >
        <div className="flex flex-row gap-x-4">
          <SidebarButton />
          <h1 className="text-[0rem]">QUA</h1>
          <Logo />
        </div>
        <div className="flex flex-row gap-x-4 items-center">
          <Globe size="1.5rem" className="stroke-teal-400" />
          <Bell size="1.5rem" className="stroke-teal-400" />
          <Kitty />
        </div>
      </header>
    </>
  );
}

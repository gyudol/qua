import { Bell, Globe } from "lucide-react";
import Link from "next/link";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from "@repo/ui/shadcn/dropdown-menu";
import { Kitty, Logo } from "@/components/common/icons";
import { SidebarButton } from "../bars/Sidebar";

export default function MainHeader() {
  return (
    <>
      <div className="w-full min-h-[4.5rem]" />
      <header
        className="
      absolute top-0  z-30
      w-full  h-[4.5rem]
      flex    flex-row
      px-4    shadow-md
      justify-between items-center
      "
      >
        <div className="flex flex-row gap-x-4">
          <SidebarButton />
          <h1 className="text-[0rem]">QUA</h1>
          <Logo />
        </div>
        <div className="flex flex-row gap-x-4 items-center">
          {/* <Globe size="1.5rem" className="stroke-teal-400" /> */}
          <GlobalButton />
          {/* <Bell size="1.5rem" className="stroke-teal-400" /> */}
          <BellButton />
          <Link href="/chat/rooms">
            <Kitty />
          </Link>
        </div>
      </header>
    </>
  );
}

function GlobalButton() {
  return (
    <DropdownMenu>
      <DropdownMenuTrigger className="p-1 hover:bg-gray-100 rounded-full">
        <Globe size="1.5rem" className="stroke-teal-400" />
      </DropdownMenuTrigger>
      <DropdownMenuContent className="rounded-lg shadow-xl">
        <DropdownMenuItem className="py-0 text-xs">
          <div className="w-full px-2 py-2 text-left flex items-center gap-2">
            한국어
          </div>
        </DropdownMenuItem>
      </DropdownMenuContent>
    </DropdownMenu>
  );
}

function BellButton() {
  return (
    <DropdownMenu>
      <DropdownMenuTrigger className="p-1 hover:bg-gray-100 rounded-full">
        <Bell size="1.5rem" className="stroke-teal-400" />
      </DropdownMenuTrigger>
      <DropdownMenuContent className="rounded-lg shadow-xl">
        <DropdownMenuItem className="py-0 text-xs">
          <div className="w-full px-2 py-2 text-left flex items-center gap-2">
            개발중
          </div>
        </DropdownMenuItem>
      </DropdownMenuContent>
    </DropdownMenu>
  );
}

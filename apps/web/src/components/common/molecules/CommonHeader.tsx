import { cn } from "@repo/ui/lib/utils";
import { Jua } from "next/font/google";
import {
  ChatButton,
  GlobalButton,
  NotificationButton,
} from "@/components/feed-tab/atoms";
import { CommonLayout, GoBackButton } from "../atoms";

const jua = Jua({ weight: "400", subsets: ["latin"] });

interface CommonHeaderProp {
  children?: React.ReactNode;
}

export default function CommonHeader({ children }: CommonHeaderProp) {
  return (
    <CommonLayout.Header
      className={cn(
        "flex justify-between items-center",
        "p-[20px_28px]",
        "rounded-b-[20px] shadow-md z-10",
        "bg-[var(--theme-color)]",
      )}
    >
      <div
        className={cn(
          "flex gap-[20px]",
          "text-white text-[20px]",
          jua.className,
        )}
      >
        <GoBackButton />
        {children}
      </div>
      <div className="flex items-center gap-[15px]">
        <GlobalButton stroke="white" />
        <NotificationButton fill="white" />
        <ChatButton />
      </div>
    </CommonLayout.Header>
  );
}

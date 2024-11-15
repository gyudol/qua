import { cn } from "@repo/ui/lib/utils";
import { Jua } from "next/font/google";
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
        "p-[45px_28px_20px]",
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
    </CommonLayout.Header>
  );
}

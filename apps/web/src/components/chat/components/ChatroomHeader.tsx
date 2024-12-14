import { cn } from "@repo/ui/lib/utils";
import { Jua } from "next/font/google";
import { MoreHorizontal } from "lucide-react";
import { CommonLayout, GoBackButton } from "@/components/common/atoms";
import { MemberProfileImage } from "@/components/profile/atoms/MemberProfileImage";

const jua = Jua({ weight: "400", subsets: ["latin"] });

interface ChatroomHeaderProp {
  chatroomUuid: string;
}

export function ChatroomHeader({ chatroomUuid }: ChatroomHeaderProp) {
  const profileImageUrl = "/dummies/members/member-001.png";
  const nickname = chatroomUuid;
  const subtext = "subtext";

  return (
    <CommonLayout.Header
      className={cn(
        "flex justify-between items-center",
        "p-[45px_28px_20px]",
        "rounded-b-[20px] shadow-md z-10",
        "bg-white",
      )}
    >
      <div className="w-full flex gap-[20px] text-[20px] items-center">
        <GoBackButton fill="var(--theme-color)" />
        <div className="flex flex-1 gap-[20px]">
          <div className="flex">
            <MemberProfileImage
              {...{ profileImageUrl, nickname, size: "3rem" }}
              link
            />
          </div>
          <div className="flex-1 flex flex-col">
            <div className={cn("h-auto font-bold", jua.className)}>
              {nickname}
            </div>
            <div className="h-4 text-sm text-gray-400">{subtext}</div>
          </div>
        </div>
        <div>
          <MoreHorizontal />
        </div>
      </div>
    </CommonLayout.Header>
  );
}

import { CommonLayout } from "@/components/common/atoms/CommonLayout";
import { Logo } from "@/components/common/icons";
import {
  ChatButton,
  GlobalButton,
  LnbMenuButton,
  NotificationButton,
} from "../atoms";

export default function FeedTabHeader() {
  return (
    <CommonLayout.Header className="flex justify-between items-center p-[45px_28px_20px] bg-white">
      <div className="flex items-center gap-[20px]">
        <LnbMenuButton />
        <Logo />
      </div>
      <div className="flex items-center gap-[15px]">
        <GlobalButton />
        <NotificationButton />
        <ChatButton />
      </div>
    </CommonLayout.Header>
  );
}

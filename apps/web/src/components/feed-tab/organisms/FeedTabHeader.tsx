import Logo from "../../common/icons/Logo";
import { CommonLayout } from "../../common/molecules/CommonLayout";
import ChatButton from "../atoms/ChatButton";
import GlobalButton from "../atoms/GlobalButton";
import LnbMenuButton from "../atoms/LnbMenuButton";
import NotificationButton from "../atoms/NotificationButton";

export default function FeedTabHeader() {
  return (
    <CommonLayout.Header className="flex justify-between items-center p-[32px_24px_16px] bg-white">
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

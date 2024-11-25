import {
  ChatButton,
  GlobalButton,
  NotificationButton,
} from '../feed-tab/atoms';

function HeaderNav() {
  return (
    <nav className="flex items-center gap-[15px] pr-[0.5rem]">
      <GlobalButton />
      <NotificationButton />
      <ChatButton />
    </nav>
  );
}

export default HeaderNav;

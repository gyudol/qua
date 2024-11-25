import { Button } from '@repo/ui/shadcn/button';
import { Logo, Menu } from '@/components/common/icons';

interface HeaderProps {
  onMenuClick: () => void;
}

function HeaderLeftMenuAndLogo({ onMenuClick }: HeaderProps) {
  return (
    <div className="flex items-center gap-[0.3125rem]">
      <Button
        variant="ghost"
        size="icon"
        className="md:hidden"
        onClick={onMenuClick}
        aria-label="메뉴 열기"
      >
        <Menu />
      </Button>
      <h1 className="text-[0rem]">Qua</h1>
      <Logo />
    </div>
  );
}

export default HeaderLeftMenuAndLogo;

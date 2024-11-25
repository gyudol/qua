import { Button } from '@repo/ui/shadcn/button';
import { X } from 'lucide-react';
import Link from 'next/link';

interface SidebarProps {
  open: boolean;
  onClose: () => void;
}

export function Sidebar({ open, onClose }: SidebarProps) {
  return (
    <>
      <div
        className={`fixed inset-0 z-40 bg-black bg-opacity-50 ${open ? '' : 'hidden'}`}
        onClick={onClose}
        aria-hidden="true"
      />

      <aside
        className={`fixed inset-y-0 left-0 z-50 w-full transform overflow-y-auto bg-white p-4 transition-transform duration-300 ease-in-out ${
          open ? 'translate-x-0' : '-translate-x-full'
        }`}
      >
        <div className="flex items-center justify-between mb-4">
          <h2 className="text-lg font-semibold">메뉴</h2>
          <Button
            variant="ghost"
            size="icon"
            onClick={onClose}
            aria-label="메뉴 닫기"
          >
            <X className="h-6 w-6" />
          </Button>
        </div>
        <nav>
          <ul className="space-y-2">
            <li>
              <Link href="#" className="block p-2 hover:bg-gray-100 rounded">
                홈
              </Link>
            </li>
            <li>
              <Link href="#" className="block p-2 hover:bg-gray-100 rounded">
                프로필
              </Link>
            </li>
            <li>
              <Link href="#" className="block p-2 hover:bg-gray-100 rounded">
                설정
              </Link>
            </li>
          </ul>
        </nav>
      </aside>
    </>
  );
}

import { X } from 'lucide-react';
import { useEffect } from 'react';

interface SidebarProps {
  open: boolean;
  onClose: () => void;
}

export function Sidebar({ open, onClose }: SidebarProps) {
  useEffect(() => {
    if (open) {
      document.body.style.overflow = 'hidden';
    } else {
      document.body.style.overflow = '';
    }
  }, [open]);

  return (
    <>
      <div
        className={`fixed inset-0 z-40 bg-black bg-opacity-50 ${open ? '' : 'hidden'}`}
        onClick={onClose}
        aria-hidden="true"
      />

      <aside
        className={`fixed inset-y-0 left-0 z-50 w-full transform overflow-y-auto p-4 transition-transform duration-300 ease-in-out ${
          open ? 'translate-x-0' : '-translate-x-full'
        }`}
        style={{
          animation: 'gradient-bg 3s infinite',
          background: 'linear-gradient(35deg, #A3E1EF, #7BD3E5, #0A99B7)',
          backgroundSize: '200% 100%',
        }}
      >
        <div className="flex items-center justify-between mb-4 p-4">
          <h2 className="text-2xl font-bold text-white">category</h2>
          <div onClick={onClose}>
            <X color="white" size={'1.5rem'} />
          </div>
        </div>
        {/* <nav>
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
        </nav> */}
      </aside>
    </>
  );
}

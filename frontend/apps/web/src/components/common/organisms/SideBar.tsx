import { X } from 'lucide-react';
import { useEffect } from 'react';
import Bubbles from '@/components/fish/Bubbles';
import { fishCategoryData } from '@/store/InitialData';
import Aquarium from '@/components/fish/Aquarium';

interface SidebarProps {
  open: boolean;
  onClose: () => void;
}

export interface FishCategory {
  id: number;
  name: string;
  image: React.FC<{ size?: number }>;
  url: string;
}

export function Sidebar({ open, onClose }: SidebarProps) {
  const data = fishCategoryData as FishCategory[];

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
        className={`fixed inset-y-0 left-0 z-50 w-full transform overflow-hidden p-4 transition-transform duration-300 ease-in-out ${
          open ? 'translate-x-0' : '-translate-x-full'
        }`}
        style={{
          animation: 'gradient-bg 3s infinite',
          background: 'linear-gradient(35deg, #A3E1EF, #7BD3E5, #0A99B7)',
          backgroundSize: '200% 100%',
        }}
      >
        <Bubbles />
        <div className="flex items-center justify-between mb-4 p-4">
          <h2 className="text-2xl font-bold text-white">category</h2>
          <button type="button" onClick={onClose} className="z-[100]">
            <X color="white" size="1.5rem" />
          </button>
        </div>
        <div className="flex flex-wrap gap-2 px-4 z-50">
          {data.map((category) => (
            <p
              key={category.name}
              className="text-white text-xs font-bold bg-[#FFFFFF60] w-fit py-1 px-2 rounded-full"
            >
              {category.name}
            </p>
          ))}
        </div>
        <Aquarium size={80} />
      </aside>
    </>
  );
}

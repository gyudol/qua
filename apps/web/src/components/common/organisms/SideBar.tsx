import { X } from "lucide-react";
import { useEffect } from "react";
import Bubbles from "@/components/fish/\bBubbles";
import { fishCategoryData } from "@/store/InitialData";

interface SidebarProps {
  open: boolean;
  onClose: () => void;
}

interface FishCategory {
  id: number;
  name: string;
  image: React.FC<{ size?: number }>;
  url: string;
}

export function Sidebar({ open, onClose }: SidebarProps) {
  const data = fishCategoryData as FishCategory[];

  useEffect(() => {
    if (open) {
      document.body.style.overflow = "hidden";
    } else {
      document.body.style.overflow = "";
    }
  }, [open]);

  return (
    <>
      <div
        className={`fixed inset-0 z-40 bg-black bg-opacity-50 ${open ? "" : "hidden"}`}
        onClick={onClose}
        aria-hidden="true"
      />

      <aside
        className={`fixed inset-y-0 left-0 z-50 w-full transform overflow-hidden p-4 transition-transform duration-300 ease-in-out ${
          open ? "translate-x-0" : "-translate-x-full"
        }`}
        style={{
          animation: "gradient-bg 3s infinite",
          background: "linear-gradient(35deg, #A3E1EF, #7BD3E5, #0A99B7)",
          backgroundSize: "200% 100%",
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
        {data.map((category) => (
          <div
            key={category.id}
            className="animate-swim absolute"
            style={{
              top: `${Math.random() * 90}%`, // 랜덤 Y축 위치
              right: `${Math.random() * 90}%`, // 랜덤 X축 위치
              zIndex: Math.floor(Math.random() * 10), // 랜덤 z-index
              filter: `blur(${Math.floor(Math.random() * 5)}px)`, // z-index에 따라 blur 조정
              animationDuration: `${Math.random() * 30 + 15}s`, // 랜덤 애니메이션 속도 (5초 ~ 10초)
              animationDelay: `${Math.random() * 3}s`, // 랜덤 시작 시간 지연 (0초 ~ 3초)
            }}
          >
            <div className="flex flex-col items-center justify-between">
              <p className="text-white text-xs font-bold bg-[#FFFFFF60] w-fit py-1 px-2 rounded-full">
                {category.name}
              </p>
              <category.image
                size={Math.floor(Math.random() * (300 - 40 + 1)) + 20}
              />
            </div>
          </div>
        ))}
      </aside>
    </>
  );
}

import { Pencil } from "lucide-react";
import Link from "next/link";
import type { Shorts } from "@/types/contents";

type ShortsEditButtonProps = Pick<Shorts, "shortsUuid">;

export function ShortsEditButton({ shortsUuid }: ShortsEditButtonProps) {
  return (
    <Link
      href={`/shorts-eidt/${shortsUuid}`}
      className="w-full px-4 py-2 text-left hover:bg-gray-50 flex items-center gap-2"
    >
      <Pencil className="w-4 h-4" />
      수정하기
    </Link>
  );
}

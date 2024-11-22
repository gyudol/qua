import { MoreHorizontal } from "lucide-react";
import { alertNotImplemented } from "@/functions/utils";

export function ShortsMoreButton() {
  return (
    <button
      type="button"
      className="p-2 rounded-full bg-gray-800 bg-opacity-50"
      onClick={() => alertNotImplemented()}
    >
      <MoreHorizontal className="w-6 h-6" />
    </button>
  );
}

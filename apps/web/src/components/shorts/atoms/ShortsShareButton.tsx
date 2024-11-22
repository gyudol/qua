import { Share2 } from "lucide-react";
import { alertNotImplemented } from "@/functions/utils";

export function ShortsShareButton() {
  return (
    <button
      type="button"
      className="p-2 rounded-full bg-gray-800 bg-opacity-50"
      onClick={() => alertNotImplemented()}
    >
      <Share2 className="w-6 h-6" />
    </button>
  );
}

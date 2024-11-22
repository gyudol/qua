import { MessageSquare } from "lucide-react";
import { useCommentDrawerContext } from "@/context/DrawerContext";

export function ShortsCommentsButton() {
  const { setOpen } = useCommentDrawerContext();

  function handleClick() {
    if (setOpen) setOpen((open) => !open);
  }

  return (
    <button
      type="button"
      className="p-2 rounded-full bg-gray-800 bg-opacity-50"
      onClick={handleClick}
    >
      <MessageSquare className="w-6 h-6" />
    </button>
  );
}

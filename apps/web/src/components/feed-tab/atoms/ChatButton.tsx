import Link from "next/link";
import { Kitty } from "@/components/common/icons";

export default function ChatButton() {
  return (
    <Link href="/chat">
      <Kitty />
    </Link>
  );
}

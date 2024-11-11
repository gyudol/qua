"use client";

import { useRouter } from "next/navigation";
import { LeftChevron } from "../icons";

export default function GoBackButton() {
  const router = useRouter();

  function handleClick() {
    router.back();
  }

  return (
    <button type="button" onClick={handleClick}>
      <LeftChevron />
    </button>
  );
}

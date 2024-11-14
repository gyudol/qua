"use client";

import { useRouter } from "next/navigation";
import { LeftChevron } from "../icons";

interface GoBackButtonProp {
  fill?: string;
}

export default function GoBackButton({ fill }: GoBackButtonProp) {
  const router = useRouter();

  function handleClick() {
    router.back();
  }

  return (
    <button type="button" onClick={handleClick}>
      <LeftChevron {...{ fill }} />
    </button>
  );
}

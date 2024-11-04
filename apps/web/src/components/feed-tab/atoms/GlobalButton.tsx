"use client";

import { Globe } from "@/components/common/icons";

export default function GlobalButton() {
  const handleClick = () => {
    // eslint-disable-next-line no-alert -- 구현 여부 확인
    alert("아직 구현되지 않은 기능입니다.");
  };

  return (
    <button type="button" onClick={handleClick}>
      <Globe />
    </button>
  );
}

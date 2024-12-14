"use client";
import React, { useLayoutEffect, useMemo, useState } from "react";

function Bubble() {
  // 거품의 랜덤한 크기와 위치를 메모화
  const [style, setStyle] = useState({});

  useLayoutEffect(() => {
    const size = Math.random() * 15 + 5; // 크기
    const leftPosition = Math.random() * 100; // x축 위치
    const bottomPosition = Math.random() * 100; // y축 위치
    const duration = Math.random() * 5 + 3; // 3초 ~ 8초
    const delay = Math.random() * 2; // 0초 ~ 2초
    setStyle({
      width: `${size}px`,
      height: `${size}px`,
      left: `${leftPosition}%`,
      bottom: `${bottomPosition}%`,
      animationDuration: `${duration}s`,
      animationDelay: `${delay}s`,
      animationFillMode: "both", // 애니메이션 유지
    });
  }, []);

  return <div className="bubble" style={style} />;
}

function Bubbles({ count = 50 }: { count?: number }) {
  const bubbles = useMemo(
    () => Array.from({ length: count }, (_, i) => <Bubble key={i} />),
    [count], // 한 번만 생성
  );

  return (
    <div
      className="
      absolute    top-0
      size-full   pointer-events-none"
    >
      {bubbles}
    </div>
  );
}

export default Bubbles;

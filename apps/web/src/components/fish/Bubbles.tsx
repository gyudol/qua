'use client';
import React, { useMemo } from 'react';

function Bubble() {
  // 거품의 랜덤한 크기와 위치를 메모화
  const style = useMemo(() => {
    const size = Math.random() * 20 + 10; // 크기
    const leftPosition = Math.random() * 100; // x축 위치
    const bottomPosition = Math.random() * 100; // y축 위치
    const duration = Math.random() * 5 + 3; // 3초 ~ 8초
    const delay = Math.random() * 2; // 0초 ~ 2초

    return {
      width: `${size}px`,
      height: `${size}px`,
      left: `${leftPosition}%`,
      bottom: `${bottomPosition}%`,
      animationDuration: `${duration}s`,
      animationDelay: `${delay}s`,
      animationFillMode: 'both', // 애니메이션 유지
    };
  }, []); // 초기 렌더링 시 한 번만 계산

  return <div className="bubble" style={style} />;
}

function Bubbles() {
  // Bubbles 배열을 메모화
  const bubbles = useMemo(
    () => Array.from({ length: 50 }, (_, i) => <Bubble key={i} />),
    [] // 한 번만 생성
  );

  return (
    <div className="z-[150] w-full h-full fixed top-0 left-0 pointer-events-none">
      {bubbles}
    </div>
  );
}

export default Bubbles;

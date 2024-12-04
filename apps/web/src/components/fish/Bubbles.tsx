function Bubble() {
  // 거품의 랜덤한 크기와 위치
  const size = Math.random() * 20 + 10;
  const leftPosition = Math.random() * 100;
  const bottomPosition = Math.random() * 100;
  const duration = Math.random() * 5 + 3; // 3초 ~ 8초
  const delay = Math.random() * 2; // 0초 ~ 2초

  return (
    <div
      className="bubble"
      style={{
        width: `${size}px`,
        height: `${size}px`,
        left: `${leftPosition}%`,
        bottom: `${bottomPosition}%`,
        animationDuration: `${duration}s`,
        animationDelay: `${delay}s`,
      }}
    />
  );
}

function Bubbles() {
  const bubbles = Array.from({ length: 50 }, (_, i) => <Bubble key={i} />); // 50개의 거품 생성

  return <div className="z-[150]">{bubbles}</div>;
}

export default Bubbles;

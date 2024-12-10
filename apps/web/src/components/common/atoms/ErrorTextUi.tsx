import { useEffect, useState } from "react";
import RandomFish from "./RandomFish";

function ErrorTextUi({
  errorText,
  isView,
}: {
  errorText: string;
  isView: boolean;
}) {
  const [animationValues, setAnimationValues] = useState({
    duration: "0s",
    delay: "0s",
  });

  useEffect(() => {
    // 클라이언트에서만 랜덤 값 설정
    setAnimationValues({
      duration: `${Math.random() * 10 + 20}s`,
      delay: `${Math.random() * 3}s`,
    });
  }, []);

  return (
    <div
      className={`animate-back-and-forth-2 absolute pointer-events-none top-[-1.2rem] left-[30%] ${
        isView ? "opacity-100" : "opacity-0"
      } transition ease-in-out duration-300`}
      style={{
        zIndex: 100,
        animationDuration: animationValues.duration,
        animationDelay: animationValues.delay,
      }}
    >
      <div className="flex flex-col items-center justify-between">
        <p className="text-white text-[0.6rem] bg-rose-400 py-1 px-4 rounded-full">
          {errorText ? errorText : "Bye ~"}
        </p>

        <div
          className="animate-flipping-2 animate-up-down"
          style={{
            animationDuration: animationValues.duration,
            animationDelay: animationValues.delay,
          }}
        >
          <RandomFish />
        </div>
      </div>
    </div>
  );
}

export default ErrorTextUi;

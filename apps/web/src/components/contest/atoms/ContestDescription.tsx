import React from "react";

function ConstestDescription() {
  return (
    <div className="w-full mt-5">
      <div className="mb-4 h-60 w-full bg-gray-300 flex items-center justify-center">
        {/* <Image></Image> */}
        {/* 콘테스트 홈 이미지에 들어갈 자리 */}
        <span className="text-gray-500">이미지 자리</span>
      </div>
      <ul className="list-disc list-inside space-y-2">
        <li className="text-gray-700 font-medium">
          우승 결과는 매주 월요일에 발표 됩니다.
        </li>
        <li className="text-gray-700 font-medium">
          투표는 1인당 1번 하실 수 있습니다.
        </li>
      </ul>
    </div>
  );
}

export default ConstestDescription;

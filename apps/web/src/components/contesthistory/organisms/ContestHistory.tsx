import React from "react";

function ContestHistory() {
  return (
    <div>
      <div className="flex justify-center items-center mt-5">
        <div className="text-6xl text-[#47D0BF]">🏆</div>
      </div>
      <div className="relative border border-gray-300 rounded-lg shadow-md p-6 bg-white max-w-md mx-auto mt-5">
        <div className="absolute -top-4 left-1/2 transform -translate-x-1/2 bg-[#47D0BF] text-white font-semibold text-sm py-1 px-4 rounded-full shadow-md">
          콘테스트 이름
        </div>
        <div className="mb-6">
          <div className="flex justify-between items-center text-sm text-gray-600 border-b pb-4 mb-4">
            <p className="font-medium">진행 기간</p>
            <p>24.12.19 ~ 24.12.25</p>
          </div>
          <div className="mb-4 h-60 w-full bg-gray-300 flex items-center justify-center rounded-lg">
            {/* <Image></Image> */}
            {/* 콘테스트 홈 이미지에 들어갈 자리 */}
            <span className="text-gray-500">이미지 자리</span>
          </div>
        </div>
        <div className="flex flex-col gap-4">
          <button
            type="button"
            className="bg-gray-100 text-[#47D0BF] font-medium text-lg py-2 px-4 rounded-lg hover:bg-gray-200 text-center shadow-sm transition whitespace-nowrap"
          >
            우승자 리스트
          </button>
        </div>
      </div>
    </div>
  );
}

export default ContestHistory;

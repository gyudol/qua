import Link from "next/link";
import React from "react";

function ContestCard() {
  return (
    <div className="border border-gray-300 rounded-lg shadow-md p-6 bg-white max-w-md mx-auto mt-5">
      <div className="mb-6 text-center">
        <h2 className="text-xl font-semibold text-gray-800">
          제 1회 콘테스트 개최!
        </h2>
      </div>

      <div className="mb-6">
        <div className="flex justify-between items-center text-sm text-gray-600 border-b pb-4 mb-4">
          <p className="font-medium">진행 기간</p>
          <p>24.12.19 ~ 24.12.25</p>
        </div>
        <div>
          <p className="font-medium text-sm text-gray-600">우승 상품</p>
          <p className="text-base font-semibold text-gray-800 mt-1">
            우승컵 배지 🏆
          </p>
        </div>
      </div>

      <div className="flex flex-col gap-4">
        <Link
          href="/contest/contestlist"
          className="bg-gray-100 text-[#47D0BF] font-medium text-lg py-2 px-4 rounded-lg hover:bg-gray-200 text-center shadow-sm transition whitespace-nowrap"
        >
          참여 목록 보기
        </Link>
        <div className="flex gap-4">
          <Link
            href="/contest/contesthistory"
            className="bg-[#47D0BF] text-white font-semibold text-lg py-2 px-2 rounded-lg hover:bg-[#3bb3a5] flex-1 text-center shadow-sm transition whitespace-nowrap"
          >
            역대 우승자 조회
          </Link>
          <Link
            href="/contest/contestform"
            className="bg-[#47D0BF] text-white font-semibold text-lg py-2 px-2 rounded-lg hover:bg-[#3bb3a5] flex-1 text-center shadow-sm transition whitespace-nowrap"
          >
            콘테스트 참여하기
          </Link>
        </div>
      </div>
    </div>
  );
}

export default ContestCard;

import Link from "next/link";
import React from "react";

function ContestMain() {
  return (
    <div>
      <div className="flex justify-center items-center mt-5">
        <div className="text-6xl text-[#47D0BF]">🏆</div>
      </div>
      {/* 카드 내용 */}
      <div className="relative border border-gray-300 rounded-lg shadow-md p-6 bg-white max-w-md mx-auto mt-5">
        <div className="absolute -top-4 left-1/2 transform -translate-x-1/2 bg-[#47D0BF] text-white font-semibold text-sm py-1 px-4 rounded-full shadow-md">
          NEW CONTEST
        </div>
        <div className="mb-6 text-center mt-4">
          <h2 className="text-xl font-semibold text-gray-800">
            금붕어 콘테스트 개최!
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
              지난 콘테스트 보기
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

      <div className="w-full my-5">
        <div className="mb-4 h-60 w-full bg-gray-300 flex items-center justify-center rounded-lg">
          {/* <Image></Image> */}
          {/* 콘테스트 홈 이미지에 들어갈 자리 */}
          <span className="text-gray-500">이미지 자리</span>
        </div>
        <div className="w-full mt-5">
          <div className="border-2 border-[#47D0BF] rounded-lg p-6 relative">
            <div className="absolute -top-4 left-1/2 transform -translate-x-1/2 bg-white px-4 text-[#47D0BF] font-bold text-lg">
              주의사항
            </div>
            <ul className="space-y-3 mt-5">
              <li className="flex items-start text-gray-700 font-medium bg-gray-100 border border-gray-300 rounded p-4">
                <span className="text-[#47D0BF] mr-3 mt-1">✔</span>
                <p>우승 결과는 매주 월요일에 발표 됩니다.</p>
              </li>
              <li className="flex items-start text-gray-700 font-medium bg-gray-100 border border-gray-300 rounded p-4">
                <span className="text-[#47D0BF] mr-3 mt-1">✔</span>
                <p>투표는 1인당 1번 하실 수 있습니다.</p>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  );
}

export default ContestMain;

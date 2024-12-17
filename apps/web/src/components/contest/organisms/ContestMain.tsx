import Link from "next/link";
import React from "react";
import Image from "next/image";
import type { Contest } from "@/types/contest/contest";
import Caution from "../atoms/Caution";

interface ContestMainProps {
  contests: Contest[];
}

function ContestMain({ contests }: ContestMainProps) {
  return (
    <div>
      <div className="flex justify-center items-center mt-5">
        <div className="text-6xl text-[#47D0BF]">🏆</div>
      </div>

      {/* 카드 내용 */}
      {contests.length > 0 && (
        <div className="relative border border-gray-300 rounded-lg shadow-md p-6 bg-white max-w-md mx-auto mt-5">
          <div className="absolute -top-4 left-1/2 transform -translate-x-1/2 bg-[#47D0BF] text-white font-semibold text-sm py-1 px-4 rounded-full shadow-md">
            NEW CONTEST
          </div>
          <div className="mb-6 text-center mt-4">
            <h2 className="text-xl font-semibold text-gray-800">
              {contests[0].contestName}
            </h2>
          </div>
          <div className="mb-6">
            <div className="flex justify-between items-center text-sm text-gray-600 border-b pb-4 mb-4">
              <p className="font-medium">진행 기간</p>
              <p>
                {contests[0].startDate} ~ {contests[0].endDate}
              </p>
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
              투표하러 가기
            </Link>
            <div className="flex gap-4">
              <Link
                href="/contest/contesthistory"
                className="bg-[#47D0BF] text-white font-semibold text-base sm:text-lg py-2 px-2 rounded-lg hover:bg-[#3bb3a5] flex-1 text-center shadow-sm transition whitespace-nowrap"
              >
                지난 콘테스트 보기
              </Link>
              <Link
                href="/contest/contestform"
                className="bg-[#47D0BF] text-white font-semibold text-base sm:text-lg py-2 px-2 rounded-lg hover:bg-[#3bb3a5] flex-1 text-center shadow-sm transition whitespace-nowrap"
              >
                콘테스트 참여하기
              </Link>
            </div>
          </div>
        </div>
      )}

      {contests.length > 0 && (
        <div className="w-full my-5">
          <div className="mb-4 h-60 w-full bg-gray-300 flex items-center justify-center rounded-lg overflow-hidden">
            <Image
              src={contests[0].imgUrl}
              alt={contests[0].contestName}
              width={500}
              height={500}
              className="object-cover w-full h-full"
              unoptimized
            />
          </div>
        </div>
      )}

      <Caution />
    </div>
  );
}

export default ContestMain;

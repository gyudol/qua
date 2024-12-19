"use client";
import React, { useEffect, useState } from "react";
import Image from "next/image";
import type { Winner } from "@/types/contest/contest";
import { getMemberNickname } from "@/actions/member-service";

interface ContestHistoryProps {
  winners: Winner[];
  imgUrl: string;
  endDate: string;
  startDate: string;
  contestName: string;
}

function ContestHistory({
  winners,
  imgUrl,
  endDate,
  startDate,
  contestName,
}: ContestHistoryProps) {
  const [showDetails, setShowDetails] = useState(false);
  const [showWinners, setShowWinners] = useState(false);
  const [winnerNames, setWinnerNames] = useState<string[]>([]);

  useEffect(() => {
    void Promise.all(
      winners.map(({ memberUuid }) => getMemberNickname({ memberUuid })),
    ).then((names: string[]) => {
      setWinnerNames(names);
    });
  }, [winners]);

  return (
    <div className="bg-slate-400 p-4 text-white rounded-xl mt-5">
      {!showDetails ? (
        <div className="flex justify-between items-center text-nowrap">
          <h2 className="text-xl font-semibold">{contestName}</h2>
          <div className="flex items-center space-x-4 text-[0.8rem]">
            <p>
              {startDate} ~ {endDate}
            </p>
          </div>
          <button
            type="button"
            className="bg-[#47D0BF] text-white px-2 py-2 rounded-lg"
            onClick={() => setShowDetails(true)}
          >
            상세 정보 보기
          </button>
        </div>
      ) : (
        <div className="relative border border-gray-300 rounded-lg shadow-md p-6 bg-white max-w-md mx-auto mt-5">
          <div className="absolute -top-4 left-1/2 transform -translate-x-1/2 bg-[#47D0BF] text-white font-semibold text-sm py-1 px-4 rounded-full shadow-md">
            {contestName}
          </div>
          <div className="mb-6">
            <div className="flex justify-between items-center text-sm text-gray-600 border-b pb-4 mb-4">
              <p className="font-medium">진행 기간</p>
              <p>
                {startDate} ~ {endDate}
              </p>
            </div>
            <div className="mb-4 h-60 w-full bg-gray-300 flex items-center justify-center rounded-lg">
              <Image
                src={imgUrl}
                alt={contestName}
                width={500}
                height={500}
                className="object-cover w-full h-full"
                unoptimized
              />
            </div>
          </div>
          <div className="flex flex-col gap-4 mt-4">
            {/* 우승자 리스트 버튼 */}
            <button
              type="button"
              className="bg-[#47D0BF] text-white font-medium text-lg py-3 px-6 rounded-lg shadow-lg hover:bg-[#3fb1a2] transition-all duration-300 text-center"
              onClick={() => setShowWinners(!showWinners)}
            >
              {showWinners ? "우승자 숨기기" : "우승자 리스트 보기"}
            </button>

            {/* 우승자 리스트 */}
            {showWinners ? (
              <div className="mt-4 bg-white border border-gray-200 rounded-lg shadow-md p-5">
                <h3 className="text-lg font-semibold mb-4 text-gray-800 text-center">
                  🏆 Top 3 Winners
                </h3>
                <div className="space-y-3">
                  {winners.map((winner, i) => (
                    <div
                      key={winner.ranking}
                      className="flex justify-between items-center text-black bg-gray-100 p-4 rounded-lg shadow-sm"
                    >
                      <span className="font-medium text-gray-900 text-sm sm:text-base">
                        {winner.ranking}. {winnerNames[i]}
                      </span>
                      <span className="text-[#47D0BF] font-bold text-sm sm:text-base">
                        {winner.voteCount} 투표 수
                      </span>
                    </div>
                  ))}
                </div>
              </div>
            ) : null}

            <button
              type="button"
              className="mt-2 bg-gray-300 text-gray-700 font-medium py-3 px-6 rounded-lg shadow hover:bg-gray-400 transition-all duration-300"
              onClick={() => {
                setShowWinners(false);
                setShowDetails(false);
              }}
            >
              접기
            </button>
          </div>
        </div>
      )}
    </div>
  );
}

export default ContestHistory;

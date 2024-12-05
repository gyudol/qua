"use client";
import React, { useState } from "react";

interface Contest {
  id: number;
  name: string;
  period: string;
  badge: string;
  winners: string[];
}

const contests: Contest[] = [
  {
    id: 1,
    name: "Goldfish Cup",
    period: "2023.11.01 - 2023.11.10",
    badge: "🐠 Goldfish",
    winners: ["Tiger Ranchu", "Oranda", "Ryukin"],
  },
  {
    id: 2,
    name: "Betta Championship",
    period: "2023.12.01 - 2023.12.15",
    badge: "🐟 Betta",
    winners: ["Halfmoon Betta", "Plakat Betta", "Crown Tail Betta"],
  },
  {
    id: 3,
    name: "Cichlid Showdown",
    period: "2024.01.10 - 2024.01.20",
    badge: "🌊 Cichlid",
    winners: ["Oscar Cichlid", "Angelfish", "Discus"],
  },
];

function ContestHistory() {
  const [showWinners, setShowWinners] = useState<Record<number, boolean>>({});

  const toggleWinners = (id: number) => {
    setShowWinners((prev) => ({
      ...prev,
      [id]: !prev[id],
    }));
  };

  return (
    <div className="min-h-screen bg-gray-100 py-10 px-6">
      <h1 className="text-3xl font-bold text-center mb-8">콘테스트 히스토리</h1>
      <div className="space-y-8">
        {contests.map((contest) => (
          <div
            key={contest.id}
            className="bg-white shadow-md rounded-lg p-8 relative flex flex-col items-center"
          >
            <div className="w-full text-center py-2 px-4 bg-gradient-to-r from-yellow-400 to-orange-500 text-white font-bold text-2xl rounded-md shadow-md mb-6">
              {contest.name}
            </div>

            <div className="w-full flex justify-between items-center">
              <div className="flex items-center w-1/3 gap-4">
                <div className="text-4xl">{contest.badge}</div>
              </div>

              <div className="flex flex-col items-end w-2/3">
                <p className="text-gray-600 text-sm mb-4">
                  기간: {contest.period}
                </p>
                <button
                  type="button"
                  onClick={() => toggleWinners(contest.id)}
                  className="bg-gradient-to-r from-purple-500 to-blue-500 text-white font-semibold py-2 px-6 rounded-full shadow-lg hover:shadow-xl hover:from-purple-600 hover:to-blue-600 transition-all"
                >
                  {showWinners[contest.id] ? "우승자 닫기" : "우승자 보기"}
                </button>
              </div>
            </div>

            {showWinners[contest.id] ? (
              <div className="absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 bg-gray-100 p-6 rounded-lg shadow-lg border border-gray-300 transition-opacity duration-300">
                <h3 className="text-xl font-semibold mb-4 text-center">
                  우승자 명단
                </h3>
                <ul className="text-gray-700 list-disc pl-5 space-y-2">
                  {contest.winners.map((winner) => (
                    <li key={winner}>{winner}</li>
                  ))}
                </ul>
              </div>
            ) : null}
          </div>
        ))}
      </div>
    </div>
  );
}

export default ContestHistory;

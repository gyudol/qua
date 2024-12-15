import React from "react";

function ContestFilter() {
  return (
    <div className="flex justify-between items-center mb-5">
      <div className="flex items-baseline gap-2">
        <h1 className="text-xl font-bold text-gray-900">작품</h1>
      </div>
      <div className="flex items-center gap-2 text-sm text-gray-700 bg-gray-100 px-3 py-1 rounded-md">
        <button
          type="button"
          className="hover:text-black hover:underline transition"
        >
          랜덤보기
        </button>
        <span className="text-gray-400">·</span>
        <button
          type="button"
          className="hover:text-black hover:underline transition"
        >
          제목순보기
        </button>
      </div>
    </div>
  );
}

export default ContestFilter;

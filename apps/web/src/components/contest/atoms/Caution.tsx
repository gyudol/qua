import React from "react";

function Caution() {
  return (
    <div>
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
  );
}

export default Caution;

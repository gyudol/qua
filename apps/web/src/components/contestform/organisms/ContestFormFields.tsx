// "use client";

// import React, { useState } from "react";
// import type { CreateFeedType } from "@/types/request/requestType";
// import ContestUploader from "../molecule/ContestUploader";

// function ContestFormFields() {
//   const [payload, setPayload] = useState<CreateFeedType>({
//     memberUuid: "test",
//     title: "",
//     content: "",
//     categoryName: "", // 기본값을 빈 문자열로 설정
//     visibility: "VISIBLE", // 기본값
//     hashtags: [],
//     mediaList: [],
//   });

//   return (
//     <form className="w-full flex flex-col gap-5 h-full">
//       <ul className="border-2 border-dashed p-5 list-disc list-inside text-left mt-5 rounded-lg">
//         <li className="pl-6">이미지만 업로드 가능합니다.</li>
//         <li className="pl-6">관상어 관련 이미지만 업로드 가능합니다.</li>
//         <li className="pl-6">이쁜 사진을 자랑해주세요</li>
//       </ul>

//       <ContestUploader setPayload={setPayload} />

//       <button
//         type="submit"
//         className="w-[400px] text-[1rem] bg-[#47D0BF] py-[25px] rounded-lg text-white text-center mb-20 fixed bottom-10 left-1/2 translate-x-[-50%]"
//       >
//         Upload now
//       </button>
//     </form>
//   );
// }

// export default ContestFormFields;

// "use client";

// import React from "react";
// import type { CreateContestType } from "@/types/request/requestType";
// import ContestUploader from "../molecule/ContestUploader";

// function ContestFormFields({
//   payload,
//   setPayload,
// }: {
//   payload: CreateContestType;
//   setPayload: React.Dispatch<React.SetStateAction<CreateContestType>>;
// }) {
//   return (
//     <fieldset className="flex flex-col gap-5 h-full">
//       {/* 업로드 가이드 */}
//       <ul className="border-2 border-dashed p-5 list-disc list-inside text-left mt-5 rounded-lg">
//         <li className="pl-6">이미지만 업로드 가능합니다.</li>
//         <li className="pl-6">관상어 관련 이미지만 업로드 가능합니다.</li>
//         <li className="pl-6">이쁜 사진을 자랑해주세요</li>
//       </ul>

//       {/* 이미지 업로더 */}
//       <ContestUploader setPayload={setPayload} />
//     </fieldset>
//   );
// }

// export default ContestFormFields;

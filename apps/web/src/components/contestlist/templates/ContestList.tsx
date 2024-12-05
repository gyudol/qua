// "use client";
// import React, { useState } from "react";

// function ContestList() {
//   const [contests, setContests] = useState([
//     {
//       id: 1,
//       imageUrl: "https://via.placeholder.com/300x200",
//       videoUrl: "",
//     },
//     {
//       id: 2,
//       imageUrl: "",
//       videoUrl: "https://www.w3schools.com/html/mov_bbb.mp4",
//     },
//     {
//       id: 3,
//       imageUrl: "https://via.placeholder.com/300x200",
//       videoUrl: "",
//     },
//     {
//       id: 4,
//       imageUrl: "",
//       videoUrl: "https://www.w3schools.com/html/mov_bbb.mp4",
//     },
//     {
//       id: 5,
//       imageUrl: "https://via.placeholder.com/300x200",
//       videoUrl: "",
//     },
//     {
//       id: 6,
//       imageUrl: "https://via.placeholder.com/300x200",
//       videoUrl: "",
//     },
//   ]);

//   return (
//     <div className="bg-slate-100 px-5 py-5 min-h-screen">
//       {/* 헤더 */}
//       <div className="flex justify-between items-center mb-5">
//         <div className="flex items-baseline gap-2">
//           <h1 className="text-xl font-bold text-gray-900">작품</h1>
//         </div>
//         <div className="flex items-center gap-2 text-sm text-gray-700">
//           <button
//             type="button"
//             className="hover:text-black hover:underline transition"
//           >
//             랜덤보기
//           </button>
//           <span className="text-gray-400">·</span>
//           <button
//             type="button"
//             className="hover:text-black hover:underline transition"
//           >
//             제목순보기
//           </button>
//         </div>
//       </div>

//       {/* 목록 출력 */}
//       <ul className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-2 gap-4 rounded-md">
//         {contests.map((contest, index) => (
//           <li
//             key={index}
//             className="bg-white p-3 rounded-lg shadow-md hover:shadow-lg transition-shadow"
//           >
//             <div
//               className="w-full h-0 rounded-md overflow-hidden relative"
//               style={{ paddingBottom: "70%" }}
//             >
//               {/* 이미지 또는 비디오 표시 */}
//               {contest.imageUrl ? (
//                 <img
//                   src={contest.imageUrl}
//                   alt={`Contest ${index + 1}`}
//                   className="absolute inset-0 w-full h-full object-cover"
//                 />
//               ) : null}
//               {contest.videoUrl ? (
//                 <video
//                   src={contest.videoUrl}
//                   className="absolute inset-0 w-full h-full object-cover"
//                   muted
//                   loop
//                   autoPlay
//                 />
//               ) : null}
//             </div>

//             {/* 선택 버튼 */}
//             <button
//               type="button"
//               className="bg-blue-500 text-white rounded-lg px-4 py-2 mt-3 w-full hover:bg-blue-600 transition"
//               onClick={() => alert(`Contest ${index + 1} 선택됨!`)}
//             >
//               투표
//             </button>
//           </li>
//         ))}
//       </ul>
//     </div>
//   );
// }

// export default ContestList;

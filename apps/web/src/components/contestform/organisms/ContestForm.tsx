// "use client";
// // import React, { useRef, useState } from "react";
// // import type { CreateContestType } from "@/types/request/requestType";
// // import ContestFormFields from "./ContestFormFields";

// function ContestForm() {
//   // // const [payload, setPayload] = useState<CreateContestType>({
//   //   mediaUrl: "",
//   //   mediaType: "",
//   // });

//   const formRef = useRef<HTMLFormElement>(null);

//   // const onSubmit = async (event: React.FormEvent) => {
//   //   event.preventDefault(); // 기본 form 제출 방지
//   //   // console.log(payload);
//   //   // await createFeed(payload); // 피드 생성 API 호출
//   // };
//   return (
//     <form
//       ref={formRef}
//       className="w-full h-[90%] flex flex-col gap-2 relative"
//       // onSubmit={(event) => {
//       //   void onSubmit(event);
//       // }}
//     >
//       {/* <ContestFormFields payload={payload} setPayload={setPayload} /> */}
//       <button
//         type="submit"
//         className="w-[400px] text-[1rem] bg-[#47D0BF] py-[25px] rounded-lg text-white text-center mb-20 fixed bottom-10 left-1/2 translate-x-[-50%]"
//       >
//         Upload now
//       </button>
//     </form>
//   );
// }

// export default ContestForm;

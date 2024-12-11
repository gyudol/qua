// ContestHome.tsx
import React from "react";
import { getContest } from "@/actions/contest/contest";
import type { Result } from "@/types/contest/contest";
import ContestMain from "../organisms/ContestMain";

async function ContestHome() {
  // API 호출하여 데이터 가져오기
  const contestData = await getContest();
  // 데이터가 성공적으로 가져왔는지 확인
  if (
    !contestData.isSuccess ||
    !contestData.result ||
    typeof contestData.result === "string"
  ) {
    return (
      <main className="w-full px-4 flex flex-col gap-4">
        <div>데이터를 불러오는 데 실패했습니다.</div>
      </main>
    );
  }

  // 결과 데이터 추출
  const { content }: Result = contestData.result;

  return (
    <main className="w-full px-4 flex flex-col gap-4">
      {/* ContestMain에 데이터 전달 */}
      <ContestMain contests={content} />
    </main>
  );
}

export default ContestHome;

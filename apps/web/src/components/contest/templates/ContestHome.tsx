import React from "react";
import { getContest } from "@/actions/contest/contest";
import ContestMain from "../organisms/ContestMain";

async function ContestHome() {
  const contestData = await getContest();

  // 결과 데이터 추출
  const contestItems = contestData.content.map((content) => content);

  return (
    <main className="w-full px-4 flex flex-col gap-4">
      <ContestMain contests={contestItems} />
    </main>
  );
}

export default ContestHome;

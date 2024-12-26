import React from "react";
import ContestHistoryMain from "@/components/contesthistory/templates/ContestHistoryMain";
import PageContainer from "@/components/@new/layouts/containers/PageContainer";

function page() {
  return (
    <PageContainer>
      <div className="size-full p-[1rem]">
        <ContestHistoryMain />
      </div>
    </PageContainer>
  );
}

export default page;

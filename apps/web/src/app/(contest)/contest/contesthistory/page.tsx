import React from "react";
import ContestHistory from "@/components/contesthistory/organisms/ContestHistory";

function page() {
  return (
    <div className="w-full px-4 flex flex-col gap-4">
      <ContestHistory />
    </div>
  );
}

export default page;

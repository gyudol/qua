import React from "react";
import ContestCard from "../atoms/ContestCard";
import ConstestDescription from "../atoms/ContestDescription";

function ContestHome() {
  return (
    <main className="w-full px-4 flex flex-col gap-3">
      <ContestCard />
      <ConstestDescription />
    </main>
  );
}

export default ContestHome;

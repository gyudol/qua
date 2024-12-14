import React from "react";
import ContestList from "@/components/contestlist/templates/ContestList";
import { getContest } from "@/actions/contest/contest";

async function page() {
  const pagination = await getContest();
  const { contestId } = pagination.content[0];
  return (
    <div>
      <ContestList {...{ contestId }} />
    </div>
  );
}

export default page;

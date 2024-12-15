import React from "react";
import ContestList from "@/components/contestlist/templates/ContestList";
import { getContest } from "@/actions/contest/contest";
import PageContainer from "@/components/@new/layouts/containers/PageContainer";

async function page({
  searchParams: { sortBy },
}: {
  searchParams: { sortBy: "LATEST" | "VOTES" };
}) {
  const pagination = await getContest();
  const { contestId } = pagination.content[0];
  return (
    <PageContainer>
      <ContestList {...{ contestId, sortBy }} />
    </PageContainer>
  );
}

export default page;

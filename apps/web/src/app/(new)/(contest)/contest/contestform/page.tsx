import { getContest } from "@/actions/contest/contest";
import PageContainer from "@/components/@new/layouts/containers/PageContainer";
import ContestWriteForm from "@/components/contestform/organisms/ContestWriteForm";

export default async function Page() {
  const response = await getContest({ sortBy: "latest", pageSize: 1 });

  // `contestId` 목록 추출
  const contestItems = response.content.map((content) => content.contestId);
  const firstContestId = contestItems[0];
  // console.log("id", contestItems);
  return (
    <PageContainer>
      <ContestWriteForm contestId={firstContestId} />
    </PageContainer>
  );
}

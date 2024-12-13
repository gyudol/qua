import { getMemberProfileByNickname } from "@/actions/member-read-service";
import PageContainer from "@/components/@new/layouts/containers/PageContainer";
import { FollowerListSection } from "@/components/@new/profile/FollowList";

export default async function page({
  params: { nickname: _nickname },
}: {
  params: { nickname: string };
}) {
  const nickname = decodeURI(_nickname);
  const { memberUuid } = await getMemberProfileByNickname({ nickname });
  return (
    <PageContainer>
      <FollowerListSection {...{ memberUuid }} />
    </PageContainer>
  );
}

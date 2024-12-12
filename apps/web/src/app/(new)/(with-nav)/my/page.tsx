import { getServerSession } from "next-auth";
import { redirect } from "next/navigation";
import { options } from "@/app/api/auth/[...nextauth]/authOption";
import { ProfilePage } from "@/components/profile/page";
import { getMemberNickname } from "@/actions/member-service";
import PageContainer from "@/components/@new/layouts/containers/PageContainer";

export default async function page() {
  const session = await getServerSession(options);
  if (!session?.user) redirect("/sign-in");
  const { memberUuid } = session.user as { memberUuid: string };
  const nickname = await getMemberNickname({ memberUuid });

  return (
    <PageContainer>
      <ProfilePage {...{ nickname: decodeURI(nickname) }} />
    </PageContainer>
  );
}

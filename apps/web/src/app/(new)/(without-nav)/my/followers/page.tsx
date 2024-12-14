import { getServerSession } from "next-auth";
import { redirect } from "next/navigation";
import { options } from "@/app/api/auth/[...nextauth]/authOption";
import PageContainer from "@/components/@new/layouts/containers/PageContainer";
import { FollowerListSection } from "@/components/@new/profile/FollowList";

export default async function page() {
  const session = await getServerSession(options);
  if (!session?.user) redirect("/sign-in");
  const { memberUuid } = session.user as { memberUuid: string };
  return (
    <PageContainer>
      <FollowerListSection {...{ memberUuid }} />
    </PageContainer>
  );
}

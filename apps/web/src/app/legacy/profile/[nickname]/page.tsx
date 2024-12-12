import { getServerSession } from "next-auth";
import { redirect } from "next/navigation";
import { options } from "@/app/api/auth/[...nextauth]/authOption";
import { ProfilePage } from "@/components/profile/page";
import PageContainer from "@/components/@new/layouts/containers/PageContainer";

export default async function page({
  params: { nickname },
}: {
  params: {
    nickname: string;
  };
}) {
  const decodedNickname = decodeURI(nickname);
  const session = await getServerSession(options);
  if (session?.user) {
    const { nickname: myNickname } = session.user as { nickname: string };
    if (decodedNickname === myNickname) redirect("/my");
  }

  return (
    <PageContainer>
      <ProfilePage {...{ nickname: decodedNickname }} />
    </PageContainer>
  );
}

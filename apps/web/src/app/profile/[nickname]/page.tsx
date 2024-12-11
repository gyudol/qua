import { getServerSession } from "next-auth";
import { redirect } from "next/navigation";
import { options } from "@/app/api/auth/[...nextauth]/authOption";
import { CommonLayout } from "@/components/common/atoms";
import { ProfilePage } from "@/components/profile/page";
import { getMemberNickname } from "@/actions/member-service";

interface PageProps {
  params: {
    nickname: string;
  };
}

export default async function page({ params: { nickname } }: PageProps) {
  const decodedNickname = decodeURI(nickname);
  const session = await getServerSession(options);
  if (session?.user) {
    const { memberUuid } = session.user as { memberUuid: string };
    const myNickname = await getMemberNickname({ memberUuid });
    if (decodedNickname === myNickname) redirect("/profile/me");
  }

  return (
    <CommonLayout.Contents className="bg-white flex flex-col gap-[20px]">
      <ProfilePage {...{ nickname: decodedNickname }} />
    </CommonLayout.Contents>
  );
}

import { getServerSession } from "next-auth";
import { redirect } from "next/navigation";
import { options } from "@/app/api/auth/[...nextauth]/authOption";
import { CommonLayout } from "@/components/common/atoms";
import { ProfilePage } from "@/components/profile/page";

export default async function page() {
  const session = await getServerSession(options);
  if (!session?.user) redirect("/");
  const { nickname } = session.user as { nickname: string };

  return (
    <CommonLayout.Contents className="bg-white flex flex-col gap-[20px]">
      <ProfilePage {...{ nickname: decodeURI(nickname) }} />
    </CommonLayout.Contents>
  );
}

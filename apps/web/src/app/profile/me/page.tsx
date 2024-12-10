import { getServerSession } from "next-auth";
import { redirect } from "next/navigation";
import { options } from "@/app/api/auth/[...nextauth]/authOption";
import { CommonLayout } from "@/components/common/atoms";
import { ProfileCardSection } from "@/components/profile/templates";

export default async function page() {
  const session = await getServerSession(options);
  if (!session?.user) redirect("/");
  const { nickname } = session.user as { nickname: string };

  return (
    <CommonLayout.Contents className="bg-white flex flex-col gap-[20px] pb-32">
      <ProfileCardSection {...{ nickname: decodeURI(nickname) }} />
    </CommonLayout.Contents>
  );
}

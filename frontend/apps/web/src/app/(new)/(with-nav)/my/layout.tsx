import { getServerSession } from "next-auth";
import { redirect } from "next/navigation";
import { getMemberNickname } from "@/actions/member-service";
import { options } from "@/app/api/auth/[...nextauth]/authOption";
import ProfileHeader from "@/components/@new/layouts/headers/ProfileHeader";

export default async function layout({
  children,
}: {
  children: React.ReactNode;
}) {
  const session = await getServerSession(options);
  if (!session?.user) redirect("/sign-in");
  const { memberUuid } = session.user as { memberUuid: string };
  const nickname = await getMemberNickname({ memberUuid });

  return (
    <>
      <ProfileHeader {...{ nickname }} />
      {children}
    </>
  );
}

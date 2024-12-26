import { getServerSession } from "next-auth";
import { redirect } from "next/navigation";
import { getMemberProfileByUuid } from "@/actions/member-read-service";
import { options } from "@/app/api/auth/[...nextauth]/authOption";
import ProfilePage from "@/components/@new/profile/ProfilePage";

export default async function page() {
  const session = await getServerSession(options);
  if (!session?.user) redirect("/sign-in");
  const { memberUuid } = session.user as { memberUuid: string };
  const profile = await getMemberProfileByUuid({ memberUuid });

  return <ProfilePage {...profile} my />;
}

import { getServerSession } from "next-auth";
import { redirect } from "next/navigation";
import { CommonLayout } from "@/components/common/atoms";
import { options } from "@/app/api/auth/[...nextauth]/authOption";
import { FollowingSection } from "@/components/settings/follow/template/FollowingSection";
import { FollowerSection } from "@/components/settings/follow/template/FollowerSection";

export default async function Page() {
  const session = await getServerSession(options);
  if (session?.user) {
    const { memberUuid } = session.user as { memberUuid: string };
    return (
      <CommonLayout.Contents className="bg-white">
        <FollowingSection {...{ memberUuid }} />
        <FollowerSection {...{ memberUuid }} />
      </CommonLayout.Contents>
    );
  }
  redirect("/settings");
}

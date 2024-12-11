import { getServerSession } from "next-auth";
import { redirect } from "next/navigation";
import { CommonLayout } from "@/components/common/atoms";
import { options } from "@/app/api/auth/[...nextauth]/authOption";
import { HashtagInterestsSection } from "@/components/settings/interests/templates/HashtagInterestsSection";
import { CategoryInterestsSection } from "@/components/settings/interests/templates/CategoryInterestsSection";

export default async function Page() {
  const session = await getServerSession(options);
  if (session?.user) {
    const { memberUuid } = session.user as { memberUuid: string };
    return (
      <CommonLayout.Contents className="bg-white">
        <HashtagInterestsSection {...{ memberUuid }} />
        <CategoryInterestsSection {...{ memberUuid }} />
      </CommonLayout.Contents>
    );
  }
  redirect("/settings");
}

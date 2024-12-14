import { getServerSession } from "next-auth";
import { redirect } from "next/navigation";
import { CommonLayout } from "@/components/common/atoms";
import { PointHistorySection } from "@/components/settings/point/template/PointHistorySection";
import { options } from "@/app/api/auth/[...nextauth]/authOption";

export default async function Page() {
  const session = await getServerSession(options);
  if (session?.user) {
    const { memberUuid } = session.user as { memberUuid: string };
    return (
      <CommonLayout.Contents className="bg-white">
        <PointHistorySection {...{ memberUuid }} />
      </CommonLayout.Contents>
    );
  }
  redirect("/settings");
}

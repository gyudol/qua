import { MessageCircle } from "lucide-react";
import { getServerSession } from "next-auth";
import { redirect } from "next/navigation";
import DetailHeader from "@/components/@new/layouts/headers/DetailHeader";
import { options } from "@/app/api/auth/[...nextauth]/authOption";

export default async function layout({
  children,
}: {
  children: React.ReactNode;
}) {
  const session = await getServerSession(options);
  if (!session?.user) return redirect("/sign-in");

  return (
    <>
      <DetailHeader
        title={
          <>
            <MessageCircle />
            채팅방 목록
          </>
        }
      />
      {children}
    </>
  );
}

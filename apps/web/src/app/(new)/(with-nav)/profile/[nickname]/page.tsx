import { getServerSession } from "next-auth";
import { redirect } from "next/navigation";
import { options } from "@/app/api/auth/[...nextauth]/authOption";
import { ProfilePage } from "@/components/profile/page";
import { getMemberNickname } from "@/actions/member-service";
import PageContainer from "@/components/@new/layouts/containers/PageContainer";
import { getMemberProfileByNickname } from "@/actions/member-read-service";
import { MemberProfileImage } from "@/components/profile/atoms/MemberProfileImage";
import { Separator } from "@repo/ui/shadcn/separator";
import { formatToNumAbbrs } from "@/functions/utils";

export default async function page({
  params: { nickname: _nickname },
}: {
  params: {
    nickname: string;
  };
}) {
  const nickname = decodeURI(_nickname);
  // const session = await getServerSession(options);
  // if (session?.user) {
  //   const { memberUuid } = session.user as { memberUuid: string };
  //   const sessionNickname = await getMemberNickname({ memberUuid });
  //   if (nickname === sessionNickname) redirect("/my");
  // }

  const {
    profileImageUrl,
    shortsCount,
    feedCount,
    followerCount,
    followingCount,
  } = await getMemberProfileByNickname({ nickname });

  return (
    <PageContainer>
      <div className="w-full flex px-[1rem] justify-between items-center gap-[2rem]">
        <MemberProfileImage {...{ profileImageUrl, nickname, size: "5rem" }} />
        <div className="flex-1 h-[3rem] px-[1rem] flex justify-between items-center">
          <div>
            <div>{formatToNumAbbrs(shortsCount + feedCount)}</div>
            <p>Posts</p>
          </div>
          <Separator orientation="vertical" />
          <div>
            <div>{formatToNumAbbrs(followerCount)}</div>
            <p>Follwers</p>
          </div>
          <Separator orientation="vertical" />
          <div>
            <div>{formatToNumAbbrs(followingCount)}</div>
            <p>Followings</p>
          </div>
        </div>
      </div>
    </PageContainer>
  );
}

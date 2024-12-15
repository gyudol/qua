import type { Metadata } from "next";
import { CustomToaster } from "@repo/ui/shadcn/customSonner";
import { getServerSession } from "next-auth";
import QueryClientProvider from "@/components/common/molecules/QueryClientProvider";
import SessionContextProvider from "@/provider/SessionContextProvider";
import "@/app/globals.css";
import "@repo/ui/styles.css";
import "swiper/css";
import MobileContainer from "@/components/@new/layouts/containers/MobileContainer";
import { getMemberNickname } from "@/actions/member-service";
import Bubbles from "@/components/@new/features/Bubbles";
import Aquarium from "@/components/@new/features/Aquarium";
import { options } from "../api/auth/[...nextauth]/authOption";

export const revalidate = 10;

export const metadata: Metadata = {
  title: {
    default: "꾸아 관상어 전문 SNS",
    template: "%s | Qua app",
  },
  description: "꾸아 관상어 전문 SNS Qua App서비스입니다.",
  icons: { icon: "/assets/images/icons/icon.png" },
  metadataBase: new URL("https://spharos5th.com"),
  openGraph: {
    url: "https://spharos5th.com",
    title: "SPHAROS 5TH",
    description: "1차프로젝트 SPHAROS 5TH",
    images: [{ url: "/assets/images/og/og_image.png" }],
  },
};

export default async function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  const session: { user?: { memberUuid: string } } | null =
    await getServerSession(options);
  const memberUuid = session?.user?.memberUuid;
  const nickname = memberUuid && (await getMemberNickname({ memberUuid }));

  return (
    <html lang="ko">
      <body className="font-['Pretendard-Regular'] sm:animate-deepblue">
        <SessionContextProvider
          {...{
            isAuthenticated: Boolean(session?.user),
            memberUuid,
            nickname,
          }}
        >
          <QueryClientProvider>
            {/* 모바일 바깥 시작 */}
            <Aquarium />
            <Bubbles count={200} />
            {/* <Aquarium/> */}
            <MobileContainer>
              {/* 모바일 화면 시작 */}
              <CustomToaster richColors />
              {children}
              {/* 모바일 화면 끝 */}
            </MobileContainer>
          </QueryClientProvider>
        </SessionContextProvider>
      </body>
    </html>
  );
}

import type { Metadata } from "next";
import type { PropsWithChildren } from "react";

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

export default function ChatroomLayout({
  children,
}: PropsWithChildren): JSX.Element {
  return <>{children}</>;
}

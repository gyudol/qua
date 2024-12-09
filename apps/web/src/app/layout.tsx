import type { Metadata } from 'next';
import { Inter } from 'next/font/google';
import { CustomToaster } from '@repo/ui/shadcn/customSonner';
import { getServerSession } from 'next-auth';
import QueryClientProvider from '@/components/common/molecules/QueryClientProvider';
import Bubbles from '@/components/fish/Bubbles';
import Aquarium from '@/components/fish/Aquarium';
import SessionContextProvider from '@/provider/SessionContextProvider';
import { options } from './api/auth/[...nextauth]/authOption';
import '@/app/globals.css';
import '@repo/ui/styles.css';
import 'swiper/css';

const inter = Inter({ subsets: ['latin'] });

export const metadata: Metadata = {
  title: {
    default: '꾸아 관상어 전문 SNS',
    template: '%s | Qua app',
  },
  description: '꾸아 관상어 전문 SNS Qua App서비스입니다.',
  icons: { icon: '/assets/images/icons/icon.png' },
  metadataBase: new URL('https://spharos5th.com'),
  openGraph: {
    url: 'https://spharos5th.com',
    title: 'SPHAROS 5TH',
    description: '1차프로젝트 SPHAROS 5TH',
    images: [{ url: '/assets/images/og/og_image.png' }],
  },
};

export default async function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  const session: { user?: { memberUuid: string } } | null =
    await getServerSession(options);

  return (
    <html lang="en">
      <body className={inter.className}>
        <SessionContextProvider
          {...{
            isAuthenticated: Boolean(session?.user),
            memberUuid: session?.user?.memberUuid,
          }}
        >
          <QueryClientProvider>
            <Bubbles />
            <Aquarium size={20} speed={200} />
            {children}
            <CustomToaster richColors />
          </QueryClientProvider>
        </SessionContextProvider>
      </body>
    </html>
  );
}

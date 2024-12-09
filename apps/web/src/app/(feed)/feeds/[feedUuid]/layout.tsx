import CloseHeader from '@/components/layouts/CloseHeader';
import InnerMobileContainer from '@/components/layouts/InnerMobileContainer';

interface FeedListLayoutProps {
  children: React.ReactNode;
}

export default function Layout({ children }: FeedListLayoutProps): JSX.Element {
  return (
    <InnerMobileContainer>
      <CloseHeader />
      {children}
    </InnerMobileContainer>
  );
}

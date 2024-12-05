import ContestHeader from "@/components/contest/atoms/ContestHeader";
import InnerMobileContainer from "@/components/layouts/InnerMobileContainer";

function Layout({ children }: { children: React.ReactNode }) {
  return (
    <InnerMobileContainer>
      <ContestHeader />
      {children}
    </InnerMobileContainer>
  );
}

export default Layout;

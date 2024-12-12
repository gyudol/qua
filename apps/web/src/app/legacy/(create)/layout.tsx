import CloseHeader from "@/components/layouts/CloseHeader";
import InnerMobileContainer from "@/components/layouts/InnerMobileContainer";

function Layout({ children }: { children: React.ReactNode }) {
  return (
    <InnerMobileContainer>
      <CloseHeader />
      {children}
    </InnerMobileContainer>
  );
}

export default Layout;

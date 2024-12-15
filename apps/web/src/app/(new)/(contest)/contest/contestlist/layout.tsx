import DetailHeader from "@/components/@new/layouts/headers/DetailHeader";
import { PartyPopper } from "lucide-react";

function Layout({ children }: { children: React.ReactNode }) {
  return (
    <>
      <DetailHeader
        title={
          <>
            <PartyPopper />
            콘테스트
          </>
        }
      />
      {children}
    </>
  );
}

export default Layout;

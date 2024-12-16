import { PartyPopper } from "lucide-react";
import DetailHeader from "@/components/@new/layouts/headers/DetailHeader";

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

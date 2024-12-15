import { Pencil } from "lucide-react";
import DetailHeader from "@/components/@new/layouts/headers/DetailHeader";

function Layout({ children }: { children: React.ReactNode }) {
  return (
    <>
      <DetailHeader
        title={
          <>
            <Pencil />
            콘테스트 참여
          </>
        }
      />
      {children}
    </>
  );
}

export default Layout;

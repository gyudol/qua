import React from "react";

export default function PageContainer({
  children,
}: {
  children: React.ReactNode;
}) {
  return <main className="size-full overflow-scroll">{children}</main>;
}

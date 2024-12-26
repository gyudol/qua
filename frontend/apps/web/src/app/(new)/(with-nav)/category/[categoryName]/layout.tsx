import React from "react";
import TitleHeader from "@/components/@new/layouts/headers/TitleHeader";

export default function layout({
  children,
  params: { categoryName },
}: {
  children: React.ReactNode;
  params: { categoryName: string };
}) {
  const decodedCategoryName = decodeURI(categoryName);
  return (
    <>
      <TitleHeader {...{ title: `📚 ${decodedCategoryName}` }} back />
      {children}
    </>
  );
}

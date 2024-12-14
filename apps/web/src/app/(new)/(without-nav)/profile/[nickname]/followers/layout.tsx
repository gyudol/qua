import TitleHeader from "@/components/@new/layouts/headers/TitleHeader";

export default function layout({ children }: { children: React.ReactNode }) {
  return (
    <>
      <TitleHeader {...{ title: "Follower List" }} back />
      {children}
    </>
  );
}

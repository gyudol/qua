import ProfileHeader from "@/components/@new/layouts/headers/ProfileHeader";

export default function layout({
  children,
  params: { nickname: _nickname },
}: {
  children: React.ReactNode;
  params: { nickname: string };
}) {
  const nickname = decodeURI(_nickname);

  return (
    <>
      <ProfileHeader {...{ nickname }} />
      {children}
    </>
  );
}

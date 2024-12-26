import PageContainer from "@/components/@new/layouts/containers/PageContainer";
import SignInForm from "@/components/signform/molecules/LoginForm";

export default function page({
  searchParams: { callbackUrl },
}: {
  searchParams: { callbackUrl?: string };
}) {
  return (
    <PageContainer>
      <SignInForm {...{ callbackUrl }} />
    </PageContainer>
  );
}

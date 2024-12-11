import Link from "next/link";
import { CommonLayout } from "@/components/common/molecules";
// import { FeedTabHeader } from "@/components/feed-tab/organisms";

export default function NotFound() {
  return (
    <>
      {/* <FeedTabHeader /> */}
      <CommonLayout.Contents>
        <h2>Not Found</h2>
        <p>Could not find requested resource</p>
        <Link href="/">Return Home</Link>
      </CommonLayout.Contents>
    </>
  );
}

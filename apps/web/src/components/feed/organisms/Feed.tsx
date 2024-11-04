import { getAllFeed } from "@/actions/feed";

export default async function Feed() {
  await getAllFeed();
  return <div>Feed</div>;
}

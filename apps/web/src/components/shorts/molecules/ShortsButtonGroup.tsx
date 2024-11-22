import {
  ShortsBookmarkButton,
  ShortsCommentsButton,
  ShortsLikeButton,
  ShortsMoreButton,
  ShortsShareButton,
} from "../atoms";

export function ShortsButtonGroup() {
  return (
    <div className="absolute bottom-12 right-3 flex flex-col gap-y-4 text-white">
      <ShortsLikeButton />
      <ShortsCommentsButton />
      <ShortsBookmarkButton />
      <ShortsShareButton />
      <ShortsMoreButton />
    </div>
  );
}

import { HashtagWithLink } from "@/components/common/atoms";
import { Profile } from "@/components/@legacy-profile/molecules";
import type { Shorts } from "@/types/contents";

type ShortsPannelProp = Shorts;

export function ShortsPannel({
  author: { nickname, profileImageUrl },
  title,
  // content,
  hashtags,
}: ShortsPannelProp) {
  return (
    <div className="absolute max-w-[calc(100%-100px)] bottom-10 left-5 flex flex-col gap-1 text-white">
      <div className="flex items-center">
        <Profile.PictureWithLink
          href={`/profile/${nickname}`}
          src={profileImageUrl}
          alt={nickname}
        />
        <span>{nickname}</span>
        <button
          type="button"
          className="bg-white text-black py-1 px-3 rounded-full"
        >
          follow
        </button>
      </div>
      <div className="line-clamp-1">{title}</div>
      {/* <div className="line-clamp-1">{content}</div> */}
      <div className="line-clamp-1">
        {hashtags.map((hashtag) => (
          <HashtagWithLink
            key={hashtag.name}
            {...{ href: `/search?hashtag=#${hashtag.name}`, hashtag }}
          />
        ))}
      </div>
    </div>
  );
}

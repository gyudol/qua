import Link from "next/link";
import { CommonLayout } from "../molecules/CommonLayout";
import Post from "../icons/Post";
import type { IconProps } from "../icons/type";
import Mypage from "../icons/Mypage";
import Home from "../icons/Home";
import Search from "../icons/Search";
import Shorts from "../icons/Shorts";

interface GbnbListItemProp {
  name: string;
  href: string;
  Icon: (props: IconProps) => JSX.Element;
}

function GbnbListItem({ href, Icon }: GbnbListItemProp) {
  return (
    <li>
      <Link
        href={href}
        className="w-[48px] h-[48px] flex justify-center items-center"
      >
        <Icon />
      </Link>
    </li>
  );
}

const GbnbListItemInfos: GbnbListItemProp[] = [
  { name: "Feeds", href: "/", Icon: Home },
  { name: "Search", href: "/search", Icon: Search },
  { name: "CreatePost", href: "/post", Icon: Post },
  { name: "Shorts", href: "/shorts", Icon: Shorts },
  { name: "Mypage", href: "/me", Icon: Mypage },
];

export default function CommonGbnb() {
  return (
    <CommonLayout.Bnb>
      <ul className="flex flex-row justify-between items-center h-[64px] px-[16px] bg-white">
        {GbnbListItemInfos.map(({ name, href, Icon }) => (
          <GbnbListItem {...{ name, href, Icon }} key={name} />
        ))}
      </ul>
    </CommonLayout.Bnb>
  );
}

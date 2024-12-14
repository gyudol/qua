import Link from "next/link";
import { CommonLayout } from "@/components/common/atoms/CommonLayout";
import type { IconProps } from "../icons/@type";
import { Home, Mypage, Post, Search, Shorts } from "../icons";

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
        className="w-[3rem] h-[3rem] flex justify-center items-center"
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
  { name: "Mypage", href: "/profile/me", Icon: Mypage },
];

export default function CommonGbnb() {
  return (
    <>
      <div className="h-[5.5rem]" />
      <CommonLayout.Bnb>
        <ul className="flex flex-row justify-between items-center py-[1.25rem] px-[1.125rem] bg-white">
          {GbnbListItemInfos.map(({ name, href, Icon }) => (
            <GbnbListItem {...{ name, href, Icon }} key={name} />
          ))}
        </ul>
      </CommonLayout.Bnb>
    </>
  );
}

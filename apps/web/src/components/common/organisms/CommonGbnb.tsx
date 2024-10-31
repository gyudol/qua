import Link from "next/link";
import { CommonLayout } from "../molecules/CommonLayout";

interface GbnbListItemProp {
	name: string;
	href: string;
	Icon: () => JSX.Element;
}

function GbnbListItem({ href, Icon }: GbnbListItemProp) {
	return (
		<li>
			<Link href={href}>
				<Icon />
			</Link>
		</li>
	);
}

function HomeIcon() {
	return <>Home</>;
}

const GbnbListItemInfos: GbnbListItemProp[] = [
	{ name: "Feeds", href: "/", Icon: HomeIcon },
	{ name: "Search", href: "/", Icon: HomeIcon },
	{ name: "CreatePost", href: "/", Icon: HomeIcon },
	{ name: "Shorts", href: "/", Icon: HomeIcon },
	{ name: "Mypage", href: "/", Icon: HomeIcon },
];

export default function CommonGbnb() {
	return (
		<CommonLayout.Bnb>
			<ul className="flex flex-row justify-around">
				{GbnbListItemInfos.map(({ name, href, Icon }) => (
					<GbnbListItem {...{ name, href, Icon }} key={name} />
				))}
			</ul>
		</CommonLayout.Bnb>
	);
}

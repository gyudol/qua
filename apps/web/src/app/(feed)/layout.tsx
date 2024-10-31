import FeedTabHeader from "../../components/feed-tab/organisms/FeedTabHeader";

export default function layout({
	children,
}: {
	children: React.ReactNode;
}): JSX.Element {
	return (
		<>
			<FeedTabHeader />
			{children}
		</>
	);
}

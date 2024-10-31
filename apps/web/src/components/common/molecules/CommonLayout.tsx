import { cn } from "@repo/ui/lib/utils";

//
function Container({ children }: Readonly<{ children: React.ReactNode }>) {
	return (
		<div
			className={cn(
				"w-full min-w-[320px] max-w-[600px]",
				"flex flex-col align-middle"
			)}
		>
			{children}
		</div>
	);
}

//
function Header({ children }: Readonly<{ children: React.ReactNode }>) {
	return <header className={cn("w-full")}>{children}</header>;
}

// Tob Navigation Bar
function Tnb({ children }: Readonly<{ children: React.ReactNode }>) {
	return <nav className="w-full">{children}</nav>;
}

//
function Contents({ children }: Readonly<{ children: React.ReactNode }>) {
	return <main className="w-full">{children}</main>;
}

// Floating Action Button
function Fab({ children }: Readonly<{ children: React.ReactNode }>) {
	return <aside>{children}</aside>;
}

//
function Footer({ children }: Readonly<{ children: React.ReactNode }>) {
	return <footer className="w-full">{children}</footer>;
}

// Bnb Navigation Bar
function Bnb({ children }: Readonly<{ children: React.ReactNode }>) {
	return <nav className="w-full">{children}</nav>;
}

export const CommonLayout = {
	Container,
	Header,
	Tnb,
	Contents,
	Fab,
	Footer,
	Bnb,
};

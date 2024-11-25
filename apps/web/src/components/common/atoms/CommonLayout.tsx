import { cn } from '@repo/ui/lib/utils';

interface CommonLayoutProps {
  className?: string;
  children: React.ReactNode;
}

const commonStyle = cn('w-full min-w-[320px] max-w-[640px]');

//
function Container({ className, children }: CommonLayoutProps) {
  return <div className={cn(commonStyle, className)}>{children}</div>;
}

//
function Header({ className, children }: CommonLayoutProps) {
  return <header className={cn(commonStyle, className)}>{children}</header>;
}

// Tob Navigation Bar
function Tnb({ className, children }: CommonLayoutProps) {
  return <nav className={cn(commonStyle, className)}>{children}</nav>;
}

//
function Contents({ className, children }: CommonLayoutProps) {
  return <main className={cn(commonStyle, className)}>{children}</main>;
}

// Floating Action Button
function Fab({ className, children }: CommonLayoutProps) {
  return <aside className={cn(className)}>{children}</aside>;
}

//
function Footer({ className, children }: CommonLayoutProps) {
  return <footer className={cn(commonStyle, className)}>{children}</footer>;
}

// Bnb Navigation Bar
function Bnb({ className, children }: CommonLayoutProps) {
  return (
    <nav className={cn(commonStyle, 'fixed bottom-0', className)}>
      {children}
    </nav>
  );
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
